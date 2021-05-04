package com.zzq.cloud.platform.service.auth.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzq.cloud.platform.domain.auth.OAuthClientDetail;
import com.zzq.cloud.platform.domain.sys.SysUser;
import com.zzq.cloud.platform.mapper.auth.OAuthClientDetailMapper;
import com.zzq.cloud.platform.mapper.sys.SysPermissionMapper;
import com.zzq.cloud.platform.mapper.sys.SysUserMapper;
import com.zzq.cloud.platform.model.dto.auth.AuthorizationCodeDto;
import com.zzq.cloud.platform.model.dto.auth.CodeLoginDto;
import com.zzq.cloud.platform.model.dto.auth.LoginDto;
import com.zzq.cloud.platform.model.dto.auth.RefreshDto;
import com.zzq.cloud.platform.model.vo.auth.AuthorizationVo;
import com.zzq.cloud.platform.model.vo.auth.OAuthClientDetailVo;
import com.zzq.cloud.platform.security.PlatformAuthorizeCodeService;
import com.zzq.cloud.platform.service.auth.IGoogleCodeService;
import com.zzq.cloud.platform.framework.BusiException;
import com.zzq.cloud.sdk.security.SecurityModel;
import com.zzq.cloud.platform.service.auth.IAuthService;
import com.zzq.cloud.platform.framework.E;
import com.zzq.cloud.platform.util.BasicUtil;
import com.zzq.redis.annotations.Lock;
import com.zzq.redis.annotations.LockKey;
import com.zzq.redis.model.LockPolicy;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.util.OAuth2Utils;
import org.springframework.security.oauth2.provider.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author ZZQ
 * @date 2021/1/25 18:37
 */
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {

    @Value("#{T(Boolean).parseBoolean('${zzq.google:false}')}")
    private Boolean isNeedCheckGoogleCode;

    private final SecurityModel securityModel;
    private final StringRedisTemplate redisTemplate;
    private final RestTemplate restTemplate;

    private final SysUserMapper userMapper;
    private final OAuthClientDetailMapper clientDetailMapper;
    private final SysPermissionMapper permissionMapper;

    private final IGoogleCodeService googleCodeService;

    private final AuthenticationManager manager;
    private final PlatformAuthorizeCodeService codeService;
    private final OAuth2RequestFactory oAuth2RequestFactory;

    @Override
    public Map<String, Object> doLogin(LoginDto params) {

        // 如果配置需要验证码 则验证用户验证码
        if (isNeedCheckGoogleCode) {
            QueryWrapper<SysUser> query = new QueryWrapper<>();
            query.eq("username", params.getUsername());
            SysUser user = userMapper.selectOne(query);
            if (ObjectUtils.isEmpty(user)) throw new BusiException(E.UN_AUTHORIZED, "用户名错误!");

            if (!googleCodeService.isValidated(user.getGoogleKey(), params.getGoogleCode()))
                throw new BusiException(E.UN_AUTHORIZED, "验证码错误!");
        }

        LinkedMultiValueMap<String, String> header = new LinkedMultiValueMap<>();
        LinkedMultiValueMap<String, String> body = new LinkedMultiValueMap<>();

        header.add("Authorization", BasicUtil.encryptHttpBasic(params.getAppId(), params.getAppSecret()));
        body.add(OAuth2Utils.GRANT_TYPE, "password");
        body.add("username", params.getUsername());
        body.add("password", params.getPassword());
        return this.sendAuthReq(header, body);
    }

    @Override
    public Map<String, Object> doCodeLogin(CodeLoginDto codeLoginDto) {

        LinkedMultiValueMap<String, String> header = new LinkedMultiValueMap<>();
        LinkedMultiValueMap<String, String> body = new LinkedMultiValueMap<>();

        OAuthClientDetail clientDetail = clientDetailMapper.selectById(codeLoginDto.getAppId());
        if (ObjectUtils.isEmpty(clientDetail)) throw new BusiException(E.INVALID_PARAMETER, "应用不存在!");

        header.add("Authorization", BasicUtil.encryptHttpBasic(codeLoginDto.getAppId(), clientDetail.getOriginalSecret()));
        body.add(OAuth2Utils.GRANT_TYPE, "authorization_code");
        body.add("code", codeLoginDto.getAuthorizationCode());
        return this.sendAuthReq(header, body);
    }

    @Override
    public Map<String, Object> doRefresh(RefreshDto refreshDto) {
        LinkedMultiValueMap<String, String> header = new LinkedMultiValueMap<>();
        LinkedMultiValueMap<String, String> body = new LinkedMultiValueMap<>();

        header.add("Authorization", BasicUtil.encryptHttpBasic(refreshDto.getAppId(), refreshDto.getAppSecret()));
        body.add(OAuth2Utils.GRANT_TYPE, "refresh_token");
        body.add("refresh_token", refreshDto.getRefreshToken());
        return this.sendAuthReq(header, body);
    }

    @Override
    @Lock(lockTime = 30, timeOut = 20 * 1000, keyPrefix = "code", policy = LockPolicy.SpinLock)
    public AuthorizationVo getAuthorizationCode(@LockKey(keyField = "appId") AuthorizationCodeDto dto, Boolean isAdmin) {
        // 加载账户应用
        OAuthClientDetailVo clientDetail = clientDetailMapper.findMyApplication(isAdmin ? null : dto.getUserId(), dto.getAppId());
        if (ObjectUtils.isEmpty(clientDetail)) throw new BusiException(E.INVALID_PASSWORD, "您暂无该应用权限!");
        if (StringUtils.isEmpty(clientDetail.getWebServerRedirectUri())) throw new BusiException("未找到重定向地址!");
        if (clientDetail.getIsPublish().compareTo(1) != 0) throw new BusiException("应用未上架!");
        Set<String> grantTypes = Arrays.stream(clientDetail.getAuthorizedGrantTypes().split(",")).collect(Collectors.toSet());
        if (!grantTypes.contains("authorization_code")) throw new BusiException(E.UN_AUTHORIZED, "该应用不支持此授权模式!");
        if (!clientDetail.getAutoapprove()) throw new BusiException(E.UN_AUTHORIZED, "该应用不允许自动授权!");

        // 加载账户签名信息
        String password = userMapper.findPasswordById(dto.getUserId());
        Authentication authentication = manager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), password));

        // 加载预授权信息
        Map<String, String> params = new HashMap<>();
        params.put(OAuth2Utils.CLIENT_ID, dto.getAppId());
        AuthorizationRequest authorizationRequest = oAuth2RequestFactory.createAuthorizationRequest(params);
        authorizationRequest.setApproved(Boolean.TRUE);
        OAuth2Request storedOAuth2Request = oAuth2RequestFactory.createOAuth2Request(authorizationRequest);
        OAuth2Authentication combinedAuth = new OAuth2Authentication(storedOAuth2Request, authentication);
        return new AuthorizationVo(codeService.createAuthorizationCode(combinedAuth), clientDetail.getWebServerRedirectUri());
    }

    private Map<String, Object> sendAuthReq(LinkedMultiValueMap<String, String> header, LinkedMultiValueMap<String, String> body) {
        Map<String, Object> bodyMap = new HashMap<>();
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(body, header);
        try {
            ResponseEntity<Map> result = restTemplate.exchange(securityModel.getOAuth2().getClient().getAccessTokenUri(), HttpMethod.POST, httpEntity, Map.class);
            bodyMap = result.getBody();
            bodyMap.put("code", 200);
        } catch (Exception e) {
            bodyMap.put("error_msg", e.getMessage());
            e.printStackTrace();
            bodyMap.put("code", E.INVALID_PASSWORD);
        }
        return bodyMap;
    }

}

package com.zzq.cloud.platform.service.auth.impl;

import com.zzq.cloud.platform.mapper.sys.SysPermissionMapper;
import com.zzq.cloud.platform.model.dto.auth.LoginDto;
import com.zzq.cloud.sdk.security.SecurityModel;
import com.zzq.cloud.platform.service.auth.IAuthService;
import com.zzq.cloud.platform.service.sys.IOAuthUserBuilder;
import com.zzq.cloud.sdk.framework.E;
import com.zzq.cloud.sdk.utils.BasicUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ZZQ
 * @date 2021/1/25 18:37
 */
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {

    private final RestTemplate restTemplate;
    private final SysPermissionMapper permissionMapper;
    private final IOAuthUserBuilder oAuthUserBuilder;

    private final StringRedisTemplate redisTemplate;

    private final SecurityModel securityModel;

    @Override
    public Map<String, Object> doLogin(LoginDto params) {

        LinkedMultiValueMap<String, String> header = new LinkedMultiValueMap<>();
        LinkedMultiValueMap<String, String> body = new LinkedMultiValueMap<>();

        header.add("Authorization", BasicUtil.encryptHttpBasic(params.getAppId(), params.getAppSecret()));
        body.add("grant_type", "password");
        body.add("username", params.getUsername());
        body.add("password", params.getPassword());
        return this.sendAuthReq(header, body);
    }

    private Map<String, Object> sendAuthReq(LinkedMultiValueMap<String, String> header, LinkedMultiValueMap<String, String> body) {
        Map<String, Object> bodyMap = new HashMap<>();
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(body, header);
        try {
            ResponseEntity<Map> result = restTemplate.exchange(securityModel.getOAuth2().getClient().getAccessTokenUri(), HttpMethod.POST, httpEntity, Map.class);
            bodyMap = result.getBody();
            bodyMap.put("code", 200);
        } catch (Exception e) {
            bodyMap.put("error_msg", "登录凭证错误");
            e.printStackTrace();
            bodyMap.put("code", E.INVALID_PASSWORD);
        }
        return bodyMap;
    }

}

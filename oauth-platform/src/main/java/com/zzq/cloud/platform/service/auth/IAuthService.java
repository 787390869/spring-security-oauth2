package com.zzq.cloud.platform.service.auth;

import com.alibaba.fastjson.JSONObject;
import com.zzq.cloud.platform.model.dto.auth.AuthorizationCodeDto;
import com.zzq.cloud.platform.model.dto.auth.CodeLoginDto;
import com.zzq.cloud.platform.model.dto.auth.LoginDto;
import com.zzq.cloud.platform.model.dto.auth.RefreshDto;
import com.zzq.cloud.platform.model.vo.auth.AuthorizationVo;

import java.util.Map;

/**
 * @author ZZQ
 * @date 2021/1/25 18:37
 */
public interface IAuthService {

    Map<String, Object> doLogin(LoginDto pmsLoginDto);

    Map<String, Object> doCodeLogin(CodeLoginDto codeLoginDto);

    Map<String, Object> doRefresh(RefreshDto refreshDto);

    AuthorizationVo getAuthorizationCode(AuthorizationCodeDto params, Boolean isAdmin);
}

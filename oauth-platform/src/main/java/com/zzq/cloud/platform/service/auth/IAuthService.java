package com.zzq.cloud.platform.service.auth;

import com.alibaba.fastjson.JSONObject;
import com.zzq.cloud.platform.model.dto.auth.LoginDto;

import java.util.Map;

/**
 * @author ZZQ
 * @date 2021/1/25 18:37
 */
public interface IAuthService {

    Map<String, Object> doLogin(LoginDto pmsLoginDto);

}

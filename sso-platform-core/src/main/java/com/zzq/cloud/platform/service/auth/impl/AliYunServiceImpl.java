package com.zzq.cloud.platform.service.auth.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.afs.model.v20180112.AuthenticateSigRequest;
import com.aliyuncs.afs.model.v20180112.AuthenticateSigResponse;
import com.zzq.cloud.platform.config.AliYunConfig;
import com.zzq.cloud.platform.model.dto.auth.CodeLoginDto;
import com.zzq.cloud.platform.model.dto.auth.LoginDto;
import com.zzq.cloud.platform.service.auth.IAliYunService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @Author ZhangZiQiang
 * @CreateTime 2021/5/7
 */
@Service
@RequiredArgsConstructor
public class AliYunServiceImpl implements IAliYunService {

    private final IAcsClient acsClient;

    @Override
    public Boolean doSmartVerify(LoginDto loginDto, String ip) {
        AuthenticateSigRequest request = new AuthenticateSigRequest();
        request.setSessionId(loginDto.getSessionId());
        request.setSig(loginDto.getSig());
        request.setToken(loginDto.getToken());
        request.setScene(loginDto.getScene());
        request.setAppKey(AliYunConfig.appKey);
        request.setRemoteIp(ip);

        try {
            AuthenticateSigResponse response = acsClient.getAcsResponse(request);
            if (response.getCode() == 100) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}

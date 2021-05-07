package com.zzq.cloud.sdk.config;

import com.zzq.cloud.sdk.exception.SSOException;
import com.zzq.cloud.sdk.request.AuthorizationCodeRequest;
import com.zzq.cloud.sdk.request.LogoutRequest;
import com.zzq.cloud.sdk.request.RefreshRequest;
import com.zzq.cloud.sdk.response.AccessToken;
import com.zzq.cloud.sdk.security.SecurityModel;
import java.util.Map;

/**
 * @author ZZQ
 * @date 2021/5/5 13:13
 */
public class SsoClient {

    private SecurityModel securityModel;

    public SsoClient(SecurityModel securityModel) {
        this.securityModel = securityModel;
    }

    public AccessToken getByCode(String appId, String code) throws SSOException {
        return AuthorizationCodeRequest.getAccessToken(securityModel.getServerAddress(), appId, code);
    }

    public AccessToken refresh(String appId, String appSecret, String refreshToken) {
        return RefreshRequest.refreshToken(securityModel.getServerAddress(),appId, appSecret, refreshToken);
    }

    public Map<String, Object> logout(String accessToken) throws SSOException {
        return LogoutRequest.doLogout(securityModel.getServerAddress(), accessToken);
    }

}

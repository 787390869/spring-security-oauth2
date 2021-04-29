package com.zzq.cloud.sdk.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zzq.cloud.sdk.framework.BusiException;
import com.zzq.cloud.sdk.security.AccessToken;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;


/**
 * @Author ZhangZiQiang
 * @CreateTime 2021/4/29
 */
@Data
@Slf4j
public class AuthorizationCodeRequest {

    private String appId;
    private String oAuthTokenUri;
    private String authorizationCode;

    public String buildUrl(String serverAddress) {
        if (serverAddress.endsWith("/")) serverAddress = serverAddress.substring(0, serverAddress.length());
        return serverAddress + "/auth/code_login";
    }

    public static AccessToken getAccessToken(String serverAddress, String appId, String code) {
        try {
            AuthorizationCodeRequest req = new AuthorizationCodeRequest();
            req.setAppId(appId);
            req.setAuthorizationCode(code);

            String body = HttpUtil.sendPost(req.buildUrl(serverAddress), req);
            JSONObject res = JSON.parseObject(body);
            Integer resCode = res.getInteger("code");
            if (resCode.compareTo(HttpStatus.OK.value()) == 0)
                return res.getObject("data", AccessToken.class);

            String msg = res.getString("msg");
            throw new BusiException(String.format("GET ACCESS_TOKEN FAILED: CODE=%s, MSG=%s", code, msg));
        } catch (Exception e) {
            log.error("获取凭证失败!", e);
            throw new BusiException("GET ACCESS_TOKEN FAILED!");
        }
    }

    public static void main(String[] args) {
        AccessToken accessToken = AuthorizationCodeRequest.getAccessToken("http://localhost:9500", "client", "OPEN-20210429170357");
        System.out.println(JSONObject.toJSONString(accessToken));
    }
}

package com.zzq.cloud.sdk.request;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zzq.cloud.sdk.exception.SSOException;
import com.zzq.cloud.sdk.response.AccessToken;
import com.zzq.cloud.sdk.utils.HttpUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;


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

    public static AccessToken getAccessToken(String serverAddress, String appId, String code) throws SSOException {
        AuthorizationCodeRequest req = new AuthorizationCodeRequest();
        req.setAppId(appId);
        req.setAuthorizationCode(code);

        try {
            String body = HttpUtil.sendPost(req.buildUrl(serverAddress), req);
            JSONObject res = JSON.parseObject(body);
            Integer resCode = res.getInteger("code");
            if (resCode.compareTo(HttpStatus.OK.value()) == 0) {
                return res.getObject("data", AccessToken.class);
            }

            String message = String.format("GET ACCESS_TOKEN FAILED: CODE=%s, MSG=%s", resCode, res.getString("msg"));
            throw new SSOException(message);
        } catch (SSOException ssoException) {
            throw new SSOException(ssoException.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new AccessToken();
    }

    public static void main(String[] args) {
        AccessToken accessToken = AuthorizationCodeRequest.getAccessToken("http://localhost:9500",
                "A107E56FB9CBBC3F453803BEDC70A72A", "OPEN-20210505174628");
        System.out.println(JSONObject.toJSONString(accessToken));
    }
}

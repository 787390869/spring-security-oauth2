package com.zzq.cloud.sdk.request;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zzq.cloud.sdk.exception.SSOException;
import com.zzq.cloud.sdk.response.AccessToken;
import com.zzq.cloud.sdk.utils.HttpUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

/**
 * @author ZZQ
 * @date 2021/5/7 21:56
 */
@Data
@Slf4j
public class RefreshRequest {

    private String appId;
    private String appSecret;
    private String refreshToken;

    private String refreshTokenUri;

    public String buildUrl(String serverAddress) {
        if (serverAddress.endsWith("/")) serverAddress = serverAddress.substring(0, serverAddress.length() - 1);
        return serverAddress + "/auth/refresh";
    }

    public static AccessToken refreshToken(String serverAddress, String appId, String appSecret, String refreshToken) throws SSOException {
        RefreshRequest req = new RefreshRequest();
        req.setAppId(appId);
        req.setAppSecret(appSecret);
        req.setRefreshToken(refreshToken);

        try {
            String body = HttpUtil.sendPost(req.buildUrl(serverAddress), req);
            JSONObject res = JSON.parseObject(body);
            Integer resCode = res.getInteger("code");
            if (resCode.compareTo(HttpStatus.OK.value()) == 0) {
                return res.getObject("data", AccessToken.class);
            }

            String message = String.format("REFRESH TOKEN FAILED: CODE=%s, MSG=%s", resCode, res.getString("msg"));
            throw new SSOException(message);
        } catch (SSOException ssoException) {
            throw new SSOException(ssoException.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new AccessToken();
    }

    public static void main(String[] args) {
        AccessToken accessToken = refreshToken("http://localhost:9500", "A107E56FB9CBBC3F453803BEDC70A72A",
                "secret", "28006278-8835-45bc-ac4d-2c9c2a7a08ea");
        System.out.println(JSONObject.toJSONString(accessToken));
    }

}

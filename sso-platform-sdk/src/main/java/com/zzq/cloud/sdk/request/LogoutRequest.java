package com.zzq.cloud.sdk.request;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zzq.cloud.sdk.exception.SSOException;
import com.zzq.cloud.sdk.utils.HttpUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ZZQ
 * @date 2021/5/5 16:52
 */
@Data
@Slf4j
public class LogoutRequest {

    private String accessToken;
    private String logoutUri;

    public String buildUrl(String serverAddress) {
        if (serverAddress.endsWith("/")) serverAddress = serverAddress.substring(0, serverAddress.length());
        return serverAddress + "/api/auth/logout";
    }

    public static Map<String, Object> doLogout(String serverAddress, String accessToken) throws SSOException {
        Map<String, Object> response = new HashMap<>();
        LogoutRequest req = new LogoutRequest();
        req.setAccessToken(accessToken);
        try {
            String body = HttpUtil.sendPost(req.buildUrl(serverAddress), null, req.getAccessToken());
            JSONObject res = JSON.parseObject(body);
            Integer resCode = res.getInteger("code");
            if (resCode.compareTo(HttpStatus.OK.value()) == 0) {
                response.put("code", HttpStatus.OK.value());
                response.put("msg", res.getString("msg"));
                return response;
            }

            String message = String.format("LOGOUT FAILED: CODE=%s, MSG=%s", resCode, res.getString("msg"));
            throw new SSOException(message);
        } catch (SSOException ssoException) {
            throw new SSOException(ssoException.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            response.put("code", HttpStatus.UNAUTHORIZED);
            response.put("msg","LOGOUT FAILED!!");
        }
        return  response;
    }
}

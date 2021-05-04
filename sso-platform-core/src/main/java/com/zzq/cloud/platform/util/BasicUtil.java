package com.zzq.cloud.platform.util;

public class BasicUtil {

    public static String encryptHttpBasic(String appId, String appSecret) {
        return "Basic " + Base64Util.encode((appId + ":" + appSecret).getBytes());
    }

}

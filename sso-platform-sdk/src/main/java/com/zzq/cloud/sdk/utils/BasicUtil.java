package com.zzq.cloud.sdk.utils;

public class BasicUtil {

    public static String encryptHttpBasic(String appId, String appSecret) {
        return "Basic " + Base64Util.encode((appId + ":" + appSecret).getBytes());
    }

}

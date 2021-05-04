package com.zzq.cloud.sdk.exception;

import org.apache.commons.lang3.StringUtils;

/**
 * @author ZZQ
 * @date 2021/5/4 23:50
 */
public class PlatformSdkException extends RuntimeException{

    private Integer code;

    private String message;

    public PlatformSdkException(int code) {
        super();
        this.code = code;
        this.message = "平台异常!";
    }

    public PlatformSdkException(String msg) {
        super();
        this.message = msg;
    }

    public PlatformSdkException(int code, String msg) {
        super();
        this.code = code;
        this.message = msg;
    }

    public PlatformSdkException(int code, String msg, Exception e) {
        super();
        this.code = code;
        this.message = msg;
    }

}

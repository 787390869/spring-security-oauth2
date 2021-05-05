package com.zzq.cloud.sdk.exception;

/**
 * @author ZZQ
 * @date 2021/5/5 19:05
 */
public class SSOException extends RuntimeException {

    private Integer code;

    public SSOException(String msg) {
        super(msg);
    }


    public SSOException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }

    public SSOException(Integer code, String msg, Throwable t) {
        super(msg, t);
        this.code = code;
    }

}

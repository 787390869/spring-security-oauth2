package com.zzq.cloud.platform.framework;

import org.apache.commons.lang3.StringUtils;

/**
 * @author ZZQ
 * @date 2020/1/9 10:54
 */
public class BusiException extends RuntimeException {

    private int code;
    private String message;

    public BusiException(int code, String msg, Exception e) {
        super();
        this.code = code;
        this.message = msg;
    }

    public BusiException(int code, String msg) {
        super();
        this.code = code;
        this.message = msg;
    }

    public BusiException(String msg) {
        super();
        this.message = msg;
    }

    public BusiException(int code) {
        super();
        this.code = code;
        String message = (String) (E.errorMap.get(code));
        if (StringUtils.isNotBlank(message)) {
            this.message = message;
        } else {
            this.message = "系统错误";
        }
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}

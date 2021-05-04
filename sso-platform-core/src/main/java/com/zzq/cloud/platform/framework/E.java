package com.zzq.cloud.platform.framework;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ZZQ
 * @date 2020/1/9 10:55
 */
public class E {

    //系统错误
    public final static int SYSTEM_ERROR = 500;

    //非法参数
    public final static int INVALID_PARAMETER = 101;

    // 未签名
    public final static int UN_SIGN = 102;

    // 签名校验失败
    public final static int INVALID_SIGN = 103;

    // 系统空指针
    public final static int SYSTEM_NULL_POINT = 104;

    // sql执行错误
    public final static int SQL_EXEC_ERROR = 105;

    //非法token
    public final static int INVALID_TOKEN = 95;

    //用户不存在
    public final static int USER_NOT_EXIST = 96;

    //密码错误
    public final static int INVALID_PASSWORD = 97;

    //权限不足
    public final static int UN_AUTHORIZED = 403;

    // 资源未找到
    public final static int NOT_FOUND = 404;

    // 逻辑错误
    public final static int LOGIC_ERROR = 303;


    public static Map<Integer, Object> errorMap = new HashMap<Integer, Object>();

    static {
        errorMap.put(E.SYSTEM_ERROR, "系统错误");
        errorMap.put(E.INVALID_PARAMETER, "非法参数");
        errorMap.put(E.INVALID_TOKEN, "登录信息过期，请重新登录");
        errorMap.put(E.USER_NOT_EXIST, "用户不存在");
        errorMap.put(E.INVALID_PASSWORD, "密码错误");
        errorMap.put(E.UN_AUTHORIZED, "权限不足");
        errorMap.put(E.NOT_FOUND, "未找到");
        errorMap.put(E.UN_SIGN, "未完成签名");
        errorMap.put(E.INVALID_SIGN, "签名信息错误");
        errorMap.put(E.SYSTEM_NULL_POINT, "系统空指针");
        errorMap.put(E.LOGIC_ERROR, "逻辑错误");
    }
}

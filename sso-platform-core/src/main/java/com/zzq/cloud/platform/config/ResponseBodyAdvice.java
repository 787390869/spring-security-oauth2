package com.zzq.cloud.platform.config;

import com.alibaba.fastjson.JSON;
import com.zzq.cloud.platform.framework.IgnoreResponseBodyAdvice;
import com.zzq.cloud.platform.framework.Result;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author ZhangZiQiang
 * @Date 2021/4/7 9:36
 **/
@RestControllerAdvice
public class ResponseBodyAdvice implements org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter methodParameter, Class clazz) {
        if (methodParameter.getMethod().isAnnotationPresent(IgnoreResponseBodyAdvice.class)) return false;
        if (methodParameter.getDeclaringClass().isAnnotationPresent(IgnoreResponseBodyAdvice.class)) return false;
        if (methodParameter.getDeclaringClass().getName().contains("swagger")) return false;
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object t, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest req, ServerHttpResponse resp) {
        if (t instanceof Result) return t;
        if (t instanceof Integer || t instanceof Boolean || t instanceof Long) return Result.toResult(t);
        if (t instanceof String) return JSON.toJSONString(Result.success(Result.SUCCESS_CODE, Result.DEFAULT_SUCCESS_MESSAGE, (String) t));
        return Result.success(t);
    }
}

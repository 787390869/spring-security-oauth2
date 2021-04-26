package com.zzq.cloud.sdk.security;

import com.zzq.cloud.sdk.annotations.User;
import com.zzq.cloud.sdk.base.BaseController;
import com.zzq.cloud.sdk.base.SecurityUser;
import org.springframework.core.MethodParameter;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @author ZZQ
 * @date 2021/1/2 23:11
 */
public class SecurityArgumentResolver extends BaseController implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(User.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        SecurityUser securityUser = getUser();
        if (ObjectUtils.isEmpty(securityUser)) {
            throw new RuntimeException("无法获取当前登录用户");
        }
        return securityUser;
    }
}

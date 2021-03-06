package com.zzq.cloud.sdk.framework;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zzq.cloud.sdk.security.SecurityUser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import java.lang.reflect.Field;

/**
 * @Author ZhangZiQiang
 * @Date 2021/1/25 19:19
 * @Param
 **/

public class BaseController {

    /** 获取登录用户 */
    protected SecurityUser getUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication();
        if (principal instanceof UsernamePasswordAuthenticationToken) {
            UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) principal;
            SecurityUser securityUser = JSON.parseObject(JSON.toJSONString(authentication.getPrincipal()), SecurityUser.class);
            return securityUser;
        } else if (principal instanceof OAuth2Authentication) {
            OAuth2Authentication authentication = (OAuth2Authentication) principal;
            SecurityUser securityUser = JSON.parseObject(JSON.toJSONString(authentication.getPrincipal()), SecurityUser.class);
            return securityUser;
        }
        return new SecurityUser();
    }

    /** 获取登录用户Id */
    protected Long getUserId() {
        return this.getUser().getUserId();
    }

    /** 获取登录用户账户名 */
    protected String getUsername() {
        SecurityUser user = this.getUser();
        return isBlank(user) ? "未登录" : user.getUsername();
    }

    /** 获取登录用户的昵称 */
    protected String getNickname() {
        SecurityUser user = this.getUser();
        return isBlank(user) ? "未登录" : user.getNickname();
    }

    /** 获取当前用户Token */
    protected String getAccessToken() {
        Object principal = SecurityContextHolder.getContext().getAuthentication();
        if (principal instanceof OAuth2Authentication) {
            OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) principal;
            JSONObject detailObj = JSONObject.parseObject(JSONObject.toJSONString(oAuth2Authentication.getDetails()));
            return detailObj.getString("tokenValue");
        }
        return "";
    }

    /** 获取当前登录用户平台Id */
    protected String getAppId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication();
        if (principal instanceof OAuth2Authentication) {
            OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) principal;
            return oAuth2Authentication.getOAuth2Request().getClientId();
        }
        return "";
    }

    protected Boolean isAdmin() {
        return this.getUser().getRoles().contains("admin") || this.getUser().getRoles().contains("ROLE_admin");
    }

    /** 判断对象中的每一个属性是否为空 */
    private static boolean isBlank(Object object) {
        if (object == null) return true;
        Class clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();

        boolean flag = true;
        for (Field field : fields) {
            field.setAccessible(true);
            Object fieldValue = null;
            String fieldName = null;
            try {
                fieldValue = field.get(object);
                fieldName = field.getName();
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
            if (fieldValue != null && !"serialVersionUID".equals(fieldName)) {
                flag = false;
                break;
            }
        }
        return flag;
    }

}

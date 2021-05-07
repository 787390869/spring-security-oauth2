package com.zzq.cloud.platform.security;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author ZZQ
 * @date 2021/1/26 17:23
 * @description token缓存 反序列化策略, redis里的数据可读
 */
public class PlatformAuthenticationSerializer implements ObjectDeserializer {

    @Override
    public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
        if (type == OAuth2Authentication.class) {
            try {
                Object o = parse(parser);
                if (o == null) {
                    return null;
                } else if (o instanceof OAuth2Authentication) {
                    return (T) o;
                }

                JSONObject jsonObject = (JSONObject) o;
                OAuth2Request request = parseOAuth2Request(jsonObject);
                AbstractAuthenticationToken authentication;
                try {
                    authentication = jsonObject
                            .getObject("userAuthentication", UsernamePasswordAuthenticationToken.class);
                } catch (JSONException e) {
                    authentication = jsonObject
                            .getObject("userAuthentication", PreAuthenticatedAuthenticationToken.class);
                }
                return (T) new OAuth2Authentication(request, authentication);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
        return null;
    }

    private OAuth2Request parseOAuth2Request(JSONObject jsonObject) {
        JSONObject json = jsonObject.getObject("oAuth2Request", JSONObject.class);
        Map<String, String> requestParameters = json.getObject("requestParameters", Map.class);
        String clientId = json.getString("clientId");
        String grantType = json.getString("grantType");
        String redirectUri = json.getString("redirectUri");
        Boolean approved = json.getBoolean("approved");
        Set<String> responseTypes = json
            .getObject("responseTypes", new TypeReference<HashSet<String>>() {
            });
        Set<String> scope = json.getObject("scope", new TypeReference<HashSet<String>>() {
        });
        Set<String> authorities = json.getObject("authorities", new TypeReference<HashSet<String>>() {
        });
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>(0);
        if (authorities != null && !authorities.isEmpty()) {
            authorities.forEach(s -> grantedAuthorities.add(new SimpleGrantedAuthority(s)));
        }
        Set<String> resourceIds = json
            .getObject("resourceIds", new TypeReference<HashSet<String>>() {
            });
        Map<String, Serializable> extensions = json
            .getObject("extensions", new TypeReference<HashMap<String, Serializable>>() {
            });

        OAuth2Request request = new OAuth2Request(requestParameters, clientId,
            grantedAuthorities, approved, scope, resourceIds, redirectUri, responseTypes, extensions);
        TokenRequest tokenRequest = new TokenRequest(requestParameters, clientId, scope, grantType);
        request.refresh(tokenRequest);
        return request;
    }


    @Override
    public int getFastMatchToken() {
        return 0;
    }

    private Object parse(DefaultJSONParser parse) {
        JSONObject object = new JSONObject(parse.lexer.isEnabled(Feature.OrderedField));
        Object parsedObject = parse.parseObject((Map) object);
        if (parsedObject instanceof JSONObject) {
            return (JSONObject) parsedObject;
        } else if (parsedObject instanceof OAuth2Authentication) {
            return parsedObject;
        } else {
            return parsedObject == null ? null : new JSONObject((Map) parsedObject);
        }
    }
}
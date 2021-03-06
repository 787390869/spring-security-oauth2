package com.zzq.cloud.platform.security;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import org.springframework.security.oauth2.common.DefaultOAuth2RefreshToken;

import java.lang.reflect.Type;

/**
 * @author ZZQ
 * @date 2021/1/26 17:23
 * @description token缓存 refreshToken 反序列化策略, redis里的数据可读
 * DefaultOAuth2RefreshToken 没有setValue方法，会导致JSON序列化为null
 */
public class PlatformRefreshTokenSerializer implements ObjectDeserializer {

    @Override
    public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
        if (type == DefaultOAuth2RefreshToken.class) {
            JSONObject jsonObject = parser.parseObject();
            String tokenId = jsonObject.getString("value");
            DefaultOAuth2RefreshToken refreshToken = new DefaultOAuth2RefreshToken(tokenId);
            return (T) refreshToken;
        }
        return null;
    }

    @Override
    public int getFastMatchToken() {
        return 0;
    }
}
package com.zzq.cloud.platform.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

/**
 * @author ZZQ
 * @date 2021/1/25 17:00
 */
@Component
public class PlatformTokenEnhancer implements TokenEnhancer {

    @Value("${zzq.access.token.prefix:[OPEN]}")
    private String DEFAULT_TOKEN_PREFIX;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        if (oAuth2AccessToken instanceof DefaultOAuth2AccessToken) {
            DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) oAuth2AccessToken;
            token.setValue(DEFAULT_TOKEN_PREFIX + "-" + oAuth2AccessToken.getValue());
            return token;
        }
        return oAuth2AccessToken;
    }
}

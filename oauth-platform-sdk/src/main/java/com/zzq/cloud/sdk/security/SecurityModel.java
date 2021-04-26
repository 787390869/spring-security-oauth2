package com.zzq.cloud.sdk.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author ZZQ
 * @date 2021/1/25 17:08
 */
@Data
@Configuration
@ConfigurationProperties(prefix = SecurityModel.Security_PREFIX)
public class SecurityModel {

    static final String Security_PREFIX = "security";

    private String serverIp;

    private String serverAddress;

    private OAuth2 oAuth2;

    @Data
    public static class OAuth2 {

        private Client client;

        private Resource resource;

        @Data
        public static class Client {

            public String accessTokenUri;

            public String userAuthorizationUri;

            public String clientId;

            public String clientSecret;

        }

        @Data
        public static class Resource {

            public String tokenInfoUri;

            public String userInfoUri;

        }

    }

}

package com.zzq.cloud.sdk.config;

import com.zzq.cloud.sdk.security.SecurityModel;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.annotation.Resource;

/**
 * @author ZZQ
 * @date 2021/5/5 13:09
 */
@Configuration
@EnableConfigurationProperties({SecurityModel.class})
public class SsoAutoConfiguration {

    @Resource(name = "securityModel")
    public SecurityModel securityModel;

    @Bean
    @ConditionalOnMissingBean
    public SsoClient ssoClient() {
        return new SsoClient(securityModel);
    }


}

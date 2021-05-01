package com.zzq.cloud.platform.security;

import com.zzq.cloud.sdk.framework.IOAuthResource;
import com.zzq.cloud.sdk.security.AuthExceptionEntryPoint;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * @author ZZQ
 * @date 2021/1/25 18:29
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.requestMatchers()
                .antMatchers(IOAuthResource.PATH + "/**")
            .and()
                .authorizeRequests()
                .antMatchers(IOAuthResource.PATH + "/**").authenticated()
            .and()
                .cors()
            .and()
                .csrf().disable();;
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.authenticationEntryPoint(new AuthExceptionEntryPoint());
    }

}

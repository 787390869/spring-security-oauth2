package com.zzq.cloud.platform.config;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.zzq.cloud.platform.service.auth.impl.GoogleCodeRepository;
import com.zzq.cloud.platform.converter.*;
import com.zzq.cloud.sdk.security.SecurityArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.spring.web.SpringfoxWebMvcConfiguration;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import static com.zzq.cloud.platform.util.DateUtil.*;

/**
 * @author ZZQ
 * @date 2021/3/31 21:58
 */
@Configuration
@ConditionalOnClass(SpringfoxWebMvcConfiguration.class)
public class WebAppConfig implements WebMvcConfigurer {

    @Value("${spring.jackson.date-format:yyyy-MM-dd HH:mm:ss}")
    private String pattern;

    @Autowired
    private GoogleCodeRepository googleCodeRepository;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new SecurityArgumentResolver());
    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");
        config.setAllowCredentials(true);
        config.addAllowedMethod("*");
        config.addAllowedHeader("*");
        UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
        configurationSource.registerCorsConfiguration("/**", config);
        return new CorsFilter(configurationSource);
    }


    @Bean
    @Primary
    @ConditionalOnClass(ObjectMapper.class)
    @ConditionalOnMissingBean
    public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
        objectMapper
                .setLocale(Locale.CHINA)
                //??????????????????????????????
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                // ??????
                .setTimeZone(TimeZone.getTimeZone(ZoneId.systemDefault()))
                //Date??????????????????
                .setDateFormat(new SimpleDateFormat(fm_yyyy_MM_dd_HHmmss, Locale.CHINA))

                //???????????????parser????????????JSON????????????????????????????????????????????????32???ASCII?????????????????????????????????????????? ???????????????????????????????????????????????????????????????????????????JSON?????????????????????????????????????????????????????????????????????????????????????????????
                .configure(JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS.mappedFeature(), true)
                // ???????????????????????????
                .configure(JsonReadFeature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER.mappedFeature(), true)
//                .findAndRegisterModules()

                //?????????spring boot + jpa/hibernate??????????????????????????????FetchType.LAZY????????????jackson????????????json??????????????????SerializationFeature.FAIL_ON_EMPTY_BEANS??????
                .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
                //??????????????????
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                //???????????????
                .configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        objectMapper.registerModule(new PlatformJacksonModule())
                .registerModule(new SimpleModule())
                .findAndRegisterModules();

        return objectMapper;
    }


    /**
     * ?????? @RequestParam(value = "date") Date date
     * date ???????????? ????????????
     */
    @Bean
    public Converter<String, Date> dateConvert() {
        return new String2DateConverter();
    }

    /**
     * ?????? @RequestParam(value = "time") LocalDate time
     */
    @Bean
    public Converter<String, LocalDate> localDateConverter() {
        return new String2LocalDateConverter();
    }

    /**
     * ?????? @RequestParam(value = "time") LocalTime time
     */
    @Bean
    public Converter<String, LocalTime> localTimeConverter() {
        return new String2LocalTimeConverter();
    }

    /**
     * ?????? @RequestParam(value = "time") LocalDateTime time
     */
    @Bean
    public Converter<String, LocalDateTime> localDateTimeConverter() {
        return new String2LocalDateTimeConverter();
    }

    @Bean(name = "googleAuthenticator")
    public GoogleAuthenticator googleAuthenticator() {
        GoogleAuthenticator googleAuthenticator = new GoogleAuthenticator();
        googleAuthenticator.setCredentialRepository(googleCodeRepository);
        return googleAuthenticator;
    }

    @Bean(name = "acsClient")
    public IAcsClient acsClient() throws ClientException {
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", AliYunConfig.accessKey, AliYunConfig.accessSecret);
        IAcsClient acsClient = new DefaultAcsClient(profile);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "afs", "afs.aliyuncs.com");
        return acsClient;
    }
}

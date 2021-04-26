package com.zzq.cloud.platform.config;

import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZZQ
 * @date 2021/3/31 21:56
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket customDocket(Environment environment) {

        Profiles profiles = Profiles.of("dev", "test");
        boolean isDocEnable = environment.acceptsProfiles(profiles);
        ParameterBuilder builder = new ParameterBuilder();
        List<Parameter> parameters = new ArrayList<>();
        builder.name("Authorization").description("用户token")
                .modelRef(new ModelRef("string")).parameterType("header")
                .required(false).build();
        parameters.add(builder.build());

        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).enable(isDocEnable)
                .select().apis(RequestHandlerSelectors.withClassAnnotation(Api.class)).build()
                .globalOperationParameters(parameters);
    }


    /*@Bean
    public Docket customDocket(Environment environment) {

        Profiles profiles = Profiles.of("dev", "test");
        boolean isDocEnable = environment.acceptsProfiles(profiles);

        RequestParameterBuilder builder = new RequestParameterBuilder();
        List<RequestParameter> parameters = new ArrayList<>();
        builder.name("Authorization").description("用户token")
                .in(ParameterType.HEADER)
                .query(q -> q.model(m -> m.scalarModel(ScalarType.STRING)))
                .required(false).build();
        parameters.add(builder.build());

        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).enable(isDocEnable)
                .select().apis(RequestHandlerSelectors.withClassAnnotation(Api.class)).build()
                .globalRequestParameters(parameters);
    }*/

    private ApiInfo apiInfo() {
        Contact contact = new Contact("SXW", "www.sxw.cn", "787390869@qq.com");
        return new ApiInfoBuilder()
                .title("Api文档")
                .version("3.0.0")
                .description("高新资助系统接口文档")
                .contact(contact)
                .build();
    }

}

package com.zzq.cloud.sdk.annotations;

import com.zzq.cloud.sdk.config.SsoAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author ZZQ
 * @date 2021/5/5 13:52
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(SsoAutoConfiguration.class)
public @interface EnableSSO {
}

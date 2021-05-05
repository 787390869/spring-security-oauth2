package com.zzq.cloud.platform;

import com.zzq.cloud.sdk.annotations.EnableSSO;
import com.zzq.redis.annotations.EnableLock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author ZhangZiQiang
 * @CreateTime 2021/4/26
 */
@EnableLock
@EnableSSO
@SpringBootApplication(scanBasePackages = {"com.zzq.cloud.platform", "com.zzq.cloud.sdk"})
public class OAuthPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(OAuthPlatformApplication.class, args);
    }

}

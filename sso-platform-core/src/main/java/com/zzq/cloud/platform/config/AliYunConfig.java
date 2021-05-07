package com.zzq.cloud.platform.config;

import com.zzq.cloud.sdk.security.SecurityModel;
import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author ZhangZiQiang
 * @CreateTime 2021/5/7
 */
@Getter
@Configuration
@ConfigurationProperties(prefix = AliYunConfig.ALIYUN_PREFIX)
public class AliYunConfig {

    public static final String ALIYUN_PREFIX = "aliyun";

    public static String accessKey = "LTAI5tAgCqEM4GPvZzJzPDFh";

    public static String accessSecret = "4Rv0OUp7inDTi8teIs2uWMYud9CvyE";

    public static String appKey = "FFFF0N00000000009EAC";

    public void setAccessKey(String accessKey) {
        AliYunConfig.accessKey = accessKey;
    }

    public void setAccessSecret(String accessSecret) {
        AliYunConfig.accessSecret = accessSecret;
    }
    public void setAppKey(String appKey) {
        AliYunConfig.appKey = appKey;
    }
}

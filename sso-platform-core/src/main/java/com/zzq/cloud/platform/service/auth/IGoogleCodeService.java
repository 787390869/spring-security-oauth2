package com.zzq.cloud.platform.service.auth;

/**
 * @Author ZhangZiQiang
 * @CreateTime 2021/4/29
 */
public interface IGoogleCodeService {

    // 根据用户名生成key
    String genKey(String username);

    // 根据google key 获取秘钥
    Integer getSecretByKey(String key);

    // 验证
    Boolean isValidated(String key, Integer secret);
}

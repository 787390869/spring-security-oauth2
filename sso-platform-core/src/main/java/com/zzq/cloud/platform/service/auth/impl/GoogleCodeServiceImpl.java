package com.zzq.cloud.platform.service.auth.impl;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import com.zzq.cloud.platform.service.auth.IGoogleCodeService;
import com.zzq.cloud.platform.framework.BusiException;
import com.zzq.cloud.platform.framework.E;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @Author ZhangZiQiang
 * @CreateTime 2021/4/29
 */
@Service
@RequiredArgsConstructor
public class GoogleCodeServiceImpl implements IGoogleCodeService {

    private final GoogleAuthenticator googleAuthenticator;

    @Override
    public String genKey(String username) {
        GoogleAuthenticatorKey authenticatorKey = googleAuthenticator.createCredentials(username);
        return authenticatorKey.getKey();
    }

    @Override
    public Integer getSecretByKey(String key) {
        return googleAuthenticator.getTotpPassword(key);
    }

    @Override
    public Boolean isValidated(String key, Integer secret) {
        return googleAuthenticator.authorize(key, secret);
    }
}

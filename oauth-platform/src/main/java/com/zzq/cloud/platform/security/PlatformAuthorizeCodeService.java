package com.zzq.cloud.platform.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class PlatformAuthorizeCodeService extends JdbcAuthorizationCodeServices {

    @Value("${zzq.authorization.code.prefix:[OPEN]}")
    private String DEFAULT_CODE_PREFIX;

    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

    public PlatformAuthorizeCodeService(DataSource dataSource) {
        super(dataSource);
    }

    public String createAuthorizationCode(OAuth2Authentication authentication) {
        String date = sdf.format(new Date());
        String code = DEFAULT_CODE_PREFIX + "-" + date;
        store(code, authentication);
        return code;
    }

}

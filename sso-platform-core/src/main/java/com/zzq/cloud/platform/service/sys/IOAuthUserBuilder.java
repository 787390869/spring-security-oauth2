package com.zzq.cloud.platform.service.sys;

import com.zzq.cloud.platform.domain.sys.OAuthUser;

/**
 * @author ZZQ
 * @date 2021/1/25 17:16
 */
public interface IOAuthUserBuilder {

    OAuthUser build(String buildInfo);

}

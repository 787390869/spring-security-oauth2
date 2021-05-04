package com.zzq.cloud.platform.service.auth.impl;

import com.zzq.cloud.platform.domain.sys.OAuthUser;
import com.zzq.cloud.platform.mapper.sys.SysUserMapper;
import com.zzq.cloud.platform.service.sys.IOAuthUserBuilder;
import com.zzq.cloud.platform.framework.BusiException;
import com.zzq.cloud.platform.framework.E;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

/**
 * @author ZZQ
 * @date 2021/1/25 17:17
 */
@Service
@RequiredArgsConstructor
public class PlatformUserDetailsBuilder implements IOAuthUserBuilder {

    private final SysUserMapper userMapper;

    @Override
    public OAuthUser build(String username) {
        OAuthUser user = userMapper.findByUsername(username);
        if (ObjectUtils.isEmpty(user)) {
            throw new BusiException(E.UN_AUTHORIZED, "用户名或密码错误");
        }

        /*if (user.getRoles().size() == 0) {
            throw new BusiException(E.UN_AUTHORIZED, "非法用户");
        }*/

        return user;
    }
}

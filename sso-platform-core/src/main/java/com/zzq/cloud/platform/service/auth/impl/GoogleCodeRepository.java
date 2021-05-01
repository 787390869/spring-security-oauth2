package com.zzq.cloud.platform.service.auth.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.warrenstrange.googleauth.ICredentialRepository;
import com.zzq.cloud.platform.domain.sys.SysUser;
import com.zzq.cloud.platform.mapper.sys.SysUserMapper;
import com.zzq.cloud.platform.service.auth.IGoogleCodeService;
import com.zzq.cloud.sdk.utils.Md5Util;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @Author ZhangZiQiang
 * @CreateTime 2021/4/29
 */
@Service
@RequiredArgsConstructor
public class GoogleCodeRepository implements ICredentialRepository {

    private final SysUserMapper userMapper;

    @Override
    public String getSecretKey(String secret) {
        return Md5Util.encodeMd5(secret);
    }

    @Override
    public void saveUserCredentials(String username, String key, int i, List<Integer> list) {
        QueryWrapper<SysUser> query = new QueryWrapper<>();
        query.eq("username", username);
        SysUser user = userMapper.selectOne(query);
        if (ObjectUtils.isNotEmpty(user)) {
            user.setGoogleKey(key);
            userMapper.updateById(user);
        }
    }
}

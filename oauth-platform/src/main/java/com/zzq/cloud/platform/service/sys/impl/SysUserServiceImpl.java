package com.zzq.cloud.platform.service.sys.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzq.cloud.platform.domain.sys.SysUser;
import com.zzq.cloud.platform.mapper.sys.SysUserMapper;
import com.zzq.cloud.platform.model.dto.sys.AddSysUserDto;
import com.zzq.cloud.platform.model.dto.sys.QuerySysUserDto;
import com.zzq.cloud.platform.model.enums.UserStateEnum;
import com.zzq.cloud.platform.model.vo.sys.SysUserVo;
import com.zzq.cloud.platform.service.sys.ISysUserService;
import com.zzq.cloud.sdk.framework.BusiException;
import com.zzq.cloud.sdk.framework.E;
import com.zzq.cloud.sdk.utils.BeanUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户Service业务层处理
 * @author ZZQ
 * @date 2021-01-25
 */
@Service
@RequiredArgsConstructor
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    private final SysUserMapper sysUserMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public IPage<SysUserVo> queryPage(QuerySysUserDto params) {
        return sysUserMapper.findPageByParams(params.page(), params);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer add(AddSysUserDto user) {

        QueryWrapper<SysUser> query = new QueryWrapper<>();
        query.eq("username", user.getUsername());
        if (ObjectUtils.isNotEmpty(sysUserMapper.selectOne(query))) {
            throw new BusiException(E.INVALID_PARAMETER, "已存在该用户");
        }

        SysUser newUser = new SysUser();
        BeanUtil.copyAllProperties(newUser, user);
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        int i = sysUserMapper.insert(newUser);

        return i;
    }

    @Override
    public Integer state(Long userId) {

        if (userId.equals(SysUser.BASE_USER_ID)) {
            throw new BusiException(E.INVALID_PARAMETER, "系统根用户无法禁用");
        }

        SysUser oldUser = sysUserMapper.selectById(userId);
        if (ObjectUtils.isEmpty(oldUser)) {
            throw new BusiException(E.INVALID_PARAMETER, "不存在该用户");
        }

        oldUser.setState(oldUser.getState().equals(UserStateEnum.ON.getCode()) ? UserStateEnum.OFF.getCode() : UserStateEnum.ON.getCode());
        return sysUserMapper.updateById(oldUser);
    }
}

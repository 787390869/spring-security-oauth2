package com.zzq.cloud.platform.service.sys.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzq.cloud.platform.domain.sys.SysPermission;
import com.zzq.cloud.platform.mapper.sys.SysPermissionMapper;
import com.zzq.cloud.platform.model.dto.sys.AddSysPermissionDto;
import com.zzq.cloud.platform.service.sys.ISysPermissionService;
import com.zzq.cloud.sdk.framework.BusiException;
import com.zzq.cloud.sdk.framework.E;
import com.zzq.cloud.sdk.utils.BeanUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 权限Service业务层处理
 *
 * @author ZZQ
 * @date 2021-01-25
 */
@Service
@RequiredArgsConstructor
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements ISysPermissionService {

    private final SysPermissionMapper permissionMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean add(AddSysPermissionDto permission) {

        QueryWrapper<SysPermission> permissionQuery = new QueryWrapper<>();
        permissionQuery.eq("perm", permission.getPerm());
        if (ObjectUtils.isNotEmpty(permissionMapper.selectOne(permissionQuery))) {
            throw new BusiException(E.INVALID_PARAMETER, "重复的权限名称");
        }

        SysPermission newPermission = new SysPermission();
        BeanUtil.copyAllProperties(newPermission, permission);
        permissionMapper.callInsert(newPermission);
        return Boolean.TRUE;
    }
}

package com.zzq.cloud.platform.service.sys.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzq.cloud.platform.domain.sys.SysRole;
import com.zzq.cloud.platform.domain.sys.SysRolePermission;
import com.zzq.cloud.platform.mapper.sys.SysPermissionMapper;
import com.zzq.cloud.platform.mapper.sys.SysRoleMapper;
import com.zzq.cloud.platform.mapper.sys.SysRolePermissionMapper;
import com.zzq.cloud.platform.model.dto.sys.AddRolePermissionDto;
import com.zzq.cloud.platform.service.sys.ISysRolePermissionService;
import com.zzq.cloud.sdk.framework.BusiException;
import com.zzq.cloud.sdk.framework.E;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 角色权限Service业务层处理
 *
 * @author ZZQ
 * @date 2021-01-25
 */
@Service
@RequiredArgsConstructor
public class SysRolePermissionServiceImpl extends ServiceImpl<SysRolePermissionMapper, SysRolePermission> implements ISysRolePermissionService {

    private final SysRoleMapper roleMapper;
    private final SysPermissionMapper permissionMapper;
    private final SysRolePermissionMapper rolePermissionMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean editRolePermission(AddRolePermissionDto params) {
        SysRole role = roleMapper.selectById(params.getRoleId());
        if (ObjectUtils.isEmpty(role)) {
            throw new BusiException(E.INVALID_PARAMETER, "该角色不存在");
        }

        Set<Long> permissions = Stream.of(params.getPermissionIds().split(",")).map(Long::parseLong).collect(Collectors.toSet());

        if (permissionMapper.isExistOtherClientPermission(role.getClientId(), permissions) > 0 ) {
            throw new BusiException("此角色不允许分配其他平台的权限!");
        }

        QueryWrapper<SysRolePermission> query = new QueryWrapper<>();
        query.eq("role_id", params.getRoleId());
        List<SysRolePermission> oldRPs = rolePermissionMapper.selectList(query);
        List<Long> oldPIds = oldRPs.stream().map(SysRolePermission::getPerId).collect(Collectors.toList());

        List<Long> removeRPs;
        List<SysRolePermission> addRPs;

        addRPs = permissions.stream().filter(newP -> !oldPIds.contains(newP)).map(newPId -> {
            SysRolePermission rp = new SysRolePermission();
            rp.setPerId(newPId);
            rp.setRoleId(params.getRoleId());
            return rp;
        }).collect(Collectors.toList());

        removeRPs = oldPIds.stream().filter(oldP -> !permissions.contains(oldP))
                .map(removeId -> rolePermissionMapper.findRemoveId(params.getRoleId(), removeId))
                .collect(Collectors.toList());

        if (removeRPs.size() > 0) {
            rolePermissionMapper.deleteBatchIds(removeRPs);
        }

        if (addRPs.size() > 0) {
            this.saveBatch(addRPs);
        }

        return Boolean.TRUE;
    }
}

package com.zzq.cloud.platform.service.sys.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzq.cloud.platform.domain.sys.SysRole;
import com.zzq.cloud.platform.domain.sys.SysUserRole;
import com.zzq.cloud.platform.mapper.sys.SysRoleMapper;
import com.zzq.cloud.platform.mapper.sys.SysUserRoleMapper;
import com.zzq.cloud.platform.service.sys.ISysUserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户角色Service业务层处理
 *
 * @author ZZQ
 * @date 2021-01-25
 */
@Service
@RequiredArgsConstructor
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements ISysUserRoleService {

    private final SysRoleMapper roleMapper;
    private final SysUserRoleMapper sysUserRoleMapper;

    @Override
    public void removeByUserId(Long userId) {
        QueryWrapper<SysUserRole> query = new QueryWrapper<>();
        query.eq("user_id", userId);
        this.remove(query);
    }

    @Override
    public void saveAll(List<Long> roleIds, Long userId) {
        List<SysUserRole> userRoles = roleIds.stream().map(roleId -> {
            SysUserRole userRole = new SysUserRole();
            userRole.setUserId(userId);
            userRole.setRoleId(roleId);
            return userRole;
        }).collect(Collectors.toList());
        this.saveBatch(userRoles);
    }

    @Override
    public void saveMany(List<String> roles, Long userId) {
        List<SysUserRole> userRoles = roles.stream().map(role -> {
            SysUserRole userRole = new SysUserRole();
            userRole.setUserId(userId);
            QueryWrapper<SysRole> query = new QueryWrapper<>();
            query.eq("role", role)
                 .eq("is_delete", 0);
            userRole.setRoleId(roleMapper.selectOne(query).getRoleId());
            return userRole;
        }).collect(Collectors.toList());
        this.saveBatch(userRoles);
    }
}

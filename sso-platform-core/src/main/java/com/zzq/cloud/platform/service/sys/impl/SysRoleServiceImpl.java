package com.zzq.cloud.platform.service.sys.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzq.cloud.platform.domain.sys.SysRole;
import com.zzq.cloud.platform.mapper.auth.OAuthClientDetailMapper;
import com.zzq.cloud.platform.mapper.sys.SysRoleMapper;
import com.zzq.cloud.platform.model.dto.sys.AddRoleDto;
import com.zzq.cloud.platform.model.dto.sys.EditRoleDto;
import com.zzq.cloud.platform.model.dto.sys.QueryRoleDto;
import com.zzq.cloud.platform.model.vo.sys.SysRoleVo;
import com.zzq.cloud.platform.service.sys.ISysRoleService;
import com.zzq.cloud.platform.framework.BusiException;
import com.zzq.cloud.platform.util.BeanUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色Service业务层处理
 *
 * @author ZZQ
 * @date 2021-01-25
 */
@Service
@RequiredArgsConstructor
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    private final SysRoleMapper roleMapper;
    private final OAuthClientDetailMapper clientDetailMapper;

    @Override
    public IPage<SysRoleVo> queryPage(QueryRoleDto roleDto) {
        return roleMapper.findByParams(roleDto.page(), roleDto);
    }

    @Override
    public List<String> queryList() {
        return roleMapper.findAll();
    }

    @Override
    public Integer state(Long roleId) {
        SysRole role = roleMapper.selectById(roleId);
        if (ObjectUtils.isEmpty(role)) throw new BusiException("不存在该角色");
        role.setState(role.getState() == 0 ? 1 : 0);
        return roleMapper.updateById(role);
    }

    @Override
    public Integer removeOne(Long roleId) {
        SysRole role = roleMapper.selectById(roleId);
        if (ObjectUtils.isEmpty(role)) throw new BusiException("不存在该角色");
        role.setIsDelete(1);
        return roleMapper.updateById(role);
    }

    @Override
    public Integer addRole(AddRoleDto roleDto) {
        QueryWrapper<SysRole> query = new QueryWrapper<>();
        query.eq("role", roleDto.getRole())
             .eq("is_delete", 0);
        if (ObjectUtils.isEmpty(clientDetailMapper.selectById(roleDto.getClientId()))) throw new BusiException("不存在该应用");
        if (ObjectUtils.isNotEmpty(roleMapper.selectOne(query))) throw new BusiException("已存在该角色");
        SysRole role = new SysRole();
        BeanUtil.copyAllProperties(role, roleDto);
        return roleMapper.insert(role);
    }

    @Override
    public Integer edit(EditRoleDto roleDto) {
        SysRole role = roleMapper.selectById(roleDto.getRoleId());
        BeanUtil.copyNotNullProperties(role, roleDto);
        return roleMapper.updateById(role);
    }

    @Override
    public List<SysRoleVo> allRole() {
        return roleMapper.allRole();
    }
}

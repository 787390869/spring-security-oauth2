package com.zzq.cloud.platform.service.sys.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzq.cloud.platform.domain.auth.OAuthClientDetail;
import com.zzq.cloud.platform.domain.sys.SysPermission;
import com.zzq.cloud.platform.mapper.auth.OAuthClientDetailMapper;
import com.zzq.cloud.platform.mapper.sys.SysPermissionMapper;
import com.zzq.cloud.platform.model.dto.sys.AddSysPermissionDto;
import com.zzq.cloud.platform.model.dto.sys.EditPermissionDto;
import com.zzq.cloud.platform.model.dto.sys.QueryPermissionDto;
import com.zzq.cloud.platform.model.vo.sys.PermissionTreeVo;
import com.zzq.cloud.platform.model.vo.sys.PermissionVo;
import com.zzq.cloud.platform.model.vo.sys.SysPermissionVo;
import com.zzq.cloud.platform.service.sys.ISysPermissionService;
import com.zzq.cloud.platform.framework.BusiException;
import com.zzq.cloud.platform.framework.E;
import com.zzq.cloud.platform.util.BeanUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    private final OAuthClientDetailMapper clientDetailMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean add(AddSysPermissionDto permission) {

        OAuthClientDetail clientDetail = clientDetailMapper.selectById(permission.getClientId());
        if (ObjectUtils.isEmpty(clientDetail)) throw new BusiException("不存在该平台");

        QueryWrapper<SysPermission> permissionQuery = new QueryWrapper<>();
        permissionQuery.eq("perm", permission.getPerm())
                        .eq("client_id", permission.getClientId());
        if (ObjectUtils.isNotEmpty(permissionMapper.selectOne(permissionQuery))) {
            throw new BusiException(E.INVALID_PARAMETER, "重复的权限名称");
        }

        SysPermission newPermission = new SysPermission();
        BeanUtil.copyAllProperties(newPermission, permission);
        permissionMapper.callInsert(newPermission);
        return Boolean.TRUE;
    }

    @Override
    public SysPermissionVo queryById(Long permissionId) {
        return permissionMapper.findById(permissionId);
    }

    @Override
    public PermissionTreeVo queryPermTree(String clientId) {
        List<PermissionVo> permissions = permissionMapper.findByClientId(clientId);
        if (permissions.size() == 0) return new PermissionTreeVo(new ArrayList<>(), new ArrayList<>());
        List<Long> perIds = permissions.stream().map(PermissionVo::getPerId).collect(Collectors.toList());
        List<PermissionVo> permissionVos = permissions.stream().filter(p -> p.getParentId() == null || p.getParentId() == 0).collect(Collectors.toList());
        Map<Long, List<PermissionVo>> permissionMap = permissions.stream().filter(p -> p.getParentId() != null).collect(Collectors.groupingBy(PermissionVo::getParentId));
        permissionVos.forEach(per -> {
            findChild(permissionMap, per);
        });
        return new PermissionTreeVo(perIds, permissionVos);
    }

    @Override
    public IPage<PermissionVo> queryPage(QueryPermissionDto permissionDto) {
        return permissionMapper.findByParams(permissionDto.page(), permissionDto);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer editPermission(EditPermissionDto permissionDto) {
        SysPermission permission = permissionMapper.selectById(permissionDto.getPerId());
        if (ObjectUtils.isEmpty(permission)) {
            throw new BusiException(E.INVALID_PARAMETER, "不存在该权限");
        }
        Long oldParentId = permission.getParentId();
        BeanUtil.copyNotNullProperties(permission, permissionDto);
        if (permissionDto.getParentId() != null && !permissionDto.getParentId().equals(oldParentId)) {
            SysPermission parent = permissionMapper.selectById(permissionDto.getParentId());
            if (ObjectUtils.isEmpty(parent)) {
                throw new BusiException(E.INVALID_PARAMETER, "父权限不存在");
            }
            permission.setParentId(parent.getParentId());
        }
        return permissionMapper.updateById(permission);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer removeOne(Long perId) {
        SysPermission permission = permissionMapper.selectById(perId);
        if (ObjectUtils.isEmpty(permission)) throw new BusiException("不存在此权限");
        permission.setIsDelete(1);
        return permissionMapper.updateById(permission);
    }

    @Override
    public List<Long> queryByRoleId(Long roleId) {
        return permissionMapper.findByRoleId(roleId);
    }

    public void findChild(Map<Long, List<PermissionVo>> collect, PermissionVo permissionVo) {
        List<PermissionVo> subNodes = collect.get(permissionVo.getPerId());
        if (collect.get(permissionVo.getPerId()) != null) {
            permissionVo.setChildren(subNodes);
            subNodes.forEach(node -> {
            });
            permissionVo.getChildren().forEach(permission -> {
                findChild(collect, permission);
            });
        }
    }
}

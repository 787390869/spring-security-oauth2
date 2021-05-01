package com.zzq.cloud.platform.service.sys;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzq.cloud.platform.domain.sys.SysPermission;
import com.zzq.cloud.platform.model.dto.sys.AddSysPermissionDto;
import com.zzq.cloud.platform.model.dto.sys.EditPermissionDto;
import com.zzq.cloud.platform.model.dto.sys.QueryPermissionDto;
import com.zzq.cloud.platform.model.vo.sys.PermissionTreeVo;
import com.zzq.cloud.platform.model.vo.sys.PermissionVo;
import com.zzq.cloud.platform.model.vo.sys.SysPermissionVo;

import java.util.List;

/**
 * 权限Service接口
 * 
 * @author ZZQ
 * @date 2021-01-25
 */
public interface ISysPermissionService extends IService<SysPermission> {

    /** 添加权限 */
    Boolean add(AddSysPermissionDto permission);

    SysPermissionVo queryById(Long permissionId);

    PermissionTreeVo queryPermTree(String clientId);

    IPage<PermissionVo> queryPage(QueryPermissionDto permissionDto);

    Integer editPermission(EditPermissionDto permissionDto);

    Integer removeOne(Long perId);

    List<Long> queryByRoleId(Long roleId);
}

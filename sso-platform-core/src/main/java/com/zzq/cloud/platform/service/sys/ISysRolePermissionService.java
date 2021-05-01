package com.zzq.cloud.platform.service.sys;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zzq.cloud.platform.domain.sys.SysRolePermission;
import com.zzq.cloud.platform.model.dto.sys.AddRolePermissionDto;

/**
 * 角色权限Service接口
 * 
 * @author ZZQ
 * @date 2021-01-25
 */
public interface ISysRolePermissionService extends IService<SysRolePermission> {

    Boolean editRolePermission(AddRolePermissionDto params);

}

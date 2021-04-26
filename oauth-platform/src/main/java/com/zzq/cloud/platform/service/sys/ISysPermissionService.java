package com.zzq.cloud.platform.service.sys;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zzq.cloud.platform.domain.sys.SysPermission;
import com.zzq.cloud.platform.model.dto.sys.AddSysPermissionDto;

/**
 * 权限Service接口
 * 
 * @author ZZQ
 * @date 2021-01-25
 */
public interface ISysPermissionService extends IService<SysPermission> {

    /** 添加权限 */
    Boolean add(AddSysPermissionDto permission);

}

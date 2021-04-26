package com.zzq.cloud.platform.controller.sys;

import com.zzq.cloud.platform.service.sys.ISysRolePermissionService;
import com.zzq.cloud.sdk.framework.IOAuthResource;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 角色权限Controller
 * @author ZZQ
 * @date 2021-01-25
 */
@Api(tags = {"角色权限控制层"})
@RestController
@RequiredArgsConstructor
@RequestMapping(IOAuthResource.PATH + "/sys/role_permission")
public class SysRolePermissionController {

    private final ISysRolePermissionService sysRolePermissionService;

}

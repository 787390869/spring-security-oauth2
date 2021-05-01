package com.zzq.cloud.platform.controller.sys;

import com.zzq.cloud.platform.service.sys.ISysRoleService;
import com.zzq.cloud.sdk.framework.IOAuthResource;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 角色Controller
 * @author ZZQ
 * @date 2021-01-25
 */
@Api(tags = {"角色控制层"})
@RestController
@RequiredArgsConstructor
@RequestMapping(IOAuthResource.PATH + "/sys/role")
public class SysRoleController {

    private final ISysRoleService sysRoleService;

}

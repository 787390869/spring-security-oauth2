package com.zzq.cloud.platform.controller.sys;

import com.zzq.cloud.platform.model.dto.sys.AddSysPermissionDto;
import com.zzq.cloud.platform.service.sys.ISysPermissionService;
import com.zzq.cloud.sdk.base.BaseController;
import com.zzq.cloud.sdk.framework.IOAuthResource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 权限Controller
 * @author ZZQ
 * @date 2021-01-25
 */
@Api(tags = {"权限控制层"})
@RestController
@RequiredArgsConstructor
@RequestMapping(IOAuthResource.PATH + "/sys/permission")
public class SysPermissionController extends BaseController {

    private final ISysPermissionService permissionService;

    @ApiOperation("添加权限")
    @PostMapping
    public Boolean add(@Valid @RequestBody AddSysPermissionDto permission) {
        return permissionService.add(permission);
    }

}

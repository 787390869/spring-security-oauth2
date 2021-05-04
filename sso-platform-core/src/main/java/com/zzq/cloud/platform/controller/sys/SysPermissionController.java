package com.zzq.cloud.platform.controller.sys;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zzq.cloud.platform.model.dto.sys.AddSysPermissionDto;
import com.zzq.cloud.platform.model.dto.sys.EditPermissionDto;
import com.zzq.cloud.platform.model.dto.sys.QueryPermissionDto;
import com.zzq.cloud.platform.model.vo.sys.PermissionTreeVo;
import com.zzq.cloud.platform.model.vo.sys.PermissionVo;
import com.zzq.cloud.platform.model.vo.sys.SysPermissionVo;
import com.zzq.cloud.platform.service.sys.ISysPermissionService;
import com.zzq.cloud.sdk.framework.BaseController;
import com.zzq.cloud.platform.framework.BusiException;
import com.zzq.cloud.sdk.framework.IOAuthResource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @ApiOperation("权限树")
    @ApiImplicitParam(name = "clientId", value = "应用标识", required = true)
    @GetMapping("/tree")
    public PermissionTreeVo permissionTree(@RequestParam(required = true, value = "clientId") String clientId) {
        if (StringUtils.isBlank(clientId)) throw new BusiException("应用标识不能为空!");
        return permissionService.queryPermTree(clientId);
    }

    @ApiOperation("权限分页查询")
    @GetMapping("/page")
    public IPage<PermissionVo> queryPage(QueryPermissionDto permissionDto) {
        if(!this.isAdmin()) permissionDto.setClientId(this.getAppId());
        return permissionService.queryPage(permissionDto);
    }

    @ApiOperation("权限详情")
    @GetMapping("/{permissionId}")
    public SysPermissionVo queryOne(@PathVariable Long permissionId) {
        return permissionService.queryById(permissionId);
    }

    @ApiOperation("编辑权限")
    @PutMapping("/edit")
    public Integer editPermission(@Valid @RequestBody EditPermissionDto permissionDto) {
        return permissionService.editPermission(permissionDto);
    }

    @ApiOperation("删除权限")
    @DeleteMapping("/{perId}")
    public Integer removeOne(@PathVariable Long perId) {
        return permissionService.removeOne(perId);
    }

    @ApiOperation("通过角色查权限")
    @GetMapping("/role/{roleId}")
    public List<Long> queryByRoleId(@PathVariable Long roleId) {
        return permissionService.queryByRoleId(roleId);
    }
}

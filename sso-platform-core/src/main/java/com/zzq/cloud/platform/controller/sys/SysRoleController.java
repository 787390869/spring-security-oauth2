package com.zzq.cloud.platform.controller.sys;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zzq.cloud.platform.model.dto.sys.AddRoleDto;
import com.zzq.cloud.platform.model.dto.sys.EditRoleDto;
import com.zzq.cloud.platform.model.dto.sys.QueryRoleDto;
import com.zzq.cloud.platform.model.vo.sys.SysRoleVo;
import com.zzq.cloud.platform.service.sys.ISysRoleService;
import com.zzq.cloud.sdk.framework.BaseController;
import com.zzq.cloud.sdk.framework.IOAuthResource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 角色Controller
 * @author ZZQ
 * @date 2021-01-25
 */
@Api(tags = {"角色控制层"})
@RestController
@RequiredArgsConstructor
@RequestMapping(IOAuthResource.PATH + "/sys/role")
public class SysRoleController extends BaseController {

    private final ISysRoleService roleService;

    @ApiOperation("分页查询")
    @GetMapping("/page")
    public IPage<SysRoleVo> queryPage(QueryRoleDto roleDto) {
        //if(!this.isAdmin()) roleDto.setClientId(this.getAppId());
        return roleService.queryPage(roleDto);
    }

    @ApiOperation("角色列表")
    @GetMapping("/list")
    public List<String> queryList() {
        return roleService.queryList();
    }

    @ApiOperation("启用/禁用")
    @PostMapping("/enable/{roleId}")
    public Integer enableRole(@PathVariable Long roleId) {
        return roleService.state(roleId);
    }

    @ApiOperation("删除角色")
    @DeleteMapping("/{roleId}")
    public Integer removeOne(@PathVariable Long roleId) {
        return roleService.removeOne(roleId);
    }

    @ApiOperation("添加角色")
    @PostMapping
    public Integer addRole(@Valid @RequestBody AddRoleDto roleDto) {
        return roleService.addRole(roleDto);
    }

    @ApiOperation("修改角色")
    @PutMapping
    public Integer editRole(@Valid @RequestBody EditRoleDto roleDto) {
        return roleService.edit(roleDto);
    }

    @ApiOperation("所有角色")
    @GetMapping("/all_role")
    public List<SysRoleVo> allRole() {
        return roleService.allRole();
    }


}

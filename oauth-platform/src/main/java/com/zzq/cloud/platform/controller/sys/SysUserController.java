package com.zzq.cloud.platform.controller.sys;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zzq.cloud.platform.model.dto.sys.AddSysUserDto;
import com.zzq.cloud.platform.model.dto.sys.QuerySysUserDto;
import com.zzq.cloud.platform.model.enums.UserSexEnum;
import com.zzq.cloud.platform.model.enums.UserStateEnum;
import com.zzq.cloud.platform.model.vo.sys.SysUserVo;
import com.zzq.cloud.platform.service.sys.ISysUserService;
import com.zzq.cloud.sdk.annotations.User;
import com.zzq.cloud.sdk.base.SecurityUser;
import com.zzq.cloud.sdk.framework.IOAuthResource;
import com.zzq.cloud.sdk.framework.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 用户Controller
 * @author ZZQ
 * @date 2021-01-25
 */
@Api(tags = {"用户控制层"})
@RestController
@RequiredArgsConstructor
@RequestMapping(IOAuthResource.PATH + "/sys/user")
public class SysUserController {

    private final ISysUserService userService;

    @ApiOperation("查询已登录用户信息")
    @GetMapping
    public SecurityUser queryOwnInfo(@User SecurityUser securityUser) {
        return securityUser;
    }

    @ApiOperation("查询用户列表(分页)")
    @GetMapping("/list")
    public Result<IPage<SysUserVo>> list(QuerySysUserDto params) {
        return Result.success(userService.queryPage(params)).withField(UserSexEnum.class).withField(UserStateEnum.class);
    }

    @ApiOperation("添加用户")
    @PostMapping
    public Integer add(@Valid @RequestBody AddSysUserDto params) {
        return userService.add(params);
    }

    @ApiOperation("禁用/启用用户")
    @DeleteMapping("/{userId}")
    public Integer state(@PathVariable Long userId) {
        return userService.state(userId);
    }

}

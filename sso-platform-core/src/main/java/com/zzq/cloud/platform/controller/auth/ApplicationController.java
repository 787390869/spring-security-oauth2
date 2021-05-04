package com.zzq.cloud.platform.controller.auth;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zzq.cloud.platform.domain.auth.OAuthClientDetail;
import com.zzq.cloud.platform.model.dto.sys.AddApplicationDto;
import com.zzq.cloud.platform.model.dto.sys.AllotApplicationDto;
import com.zzq.cloud.platform.model.dto.sys.EditApplicationDto;
import com.zzq.cloud.platform.model.dto.sys.QueryApplicationDto;
import com.zzq.cloud.platform.model.enums.GrantTypeEnum;
import com.zzq.cloud.platform.model.vo.auth.ApplicationVo;
import com.zzq.cloud.platform.model.vo.auth.OAuthClientDetailVo;
import com.zzq.cloud.platform.model.vo.sys.UserApplicationVo;
import com.zzq.cloud.platform.service.auth.IApplicationService;
import com.zzq.cloud.sdk.base.BaseController;
import com.zzq.cloud.sdk.framework.IOAuthResource;
import com.zzq.cloud.sdk.framework.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author ZhangZiQiang
 * @CreateTime 2021/4/30
 */
@Api(tags = "应用控制层")
@RestController
@RequestMapping(IOAuthResource.PATH + "/app")
@RequiredArgsConstructor
public class ApplicationController extends BaseController {

    private final IApplicationService applicationService;

    @ApiOperation("应用列表(map)")
    @GetMapping("/list")
    public List<ApplicationVo> queryList(QueryApplicationDto params) {
        if (!this.isAdmin()) params.setUserId(this.getUserId());
        return applicationService.queryList(params);
    }

    @ApiOperation("应用列表")
    @GetMapping("/all")
    public List<OAuthClientDetailVo> queryAll(QueryApplicationDto params) {
        if (!this.isAdmin()) params.setUserId(this.getUserId());
        return applicationService.queryAll(params);
    }

    @ApiOperation("应用列表(分页)")
    @GetMapping("/page")
    public Result<IPage<OAuthClientDetailVo>> queryPage(QueryApplicationDto params) {
        if (!this.isAdmin()) {
            params.setUserId(this.getUserId());
            params.setUsername(this.getUsername());
        }
        return Result.success(applicationService.queryPage(params)).withField(GrantTypeEnum.class);
    }

    @ApiOperation("编辑应用信息")
    @PostMapping("/edit")
    public Integer editApplication(@Valid @RequestBody EditApplicationDto applicationDto) {
        return applicationService.edit(applicationDto);
    }

    @ApiOperation("上架/下架")
    @PutMapping("/{appId}")
    public Integer publish(@PathVariable String appId) {
        return applicationService.publish(appId);
    }

    @ApiOperation("删除应用")
    @DeleteMapping("/{appId}")
    public Integer removeOne(@PathVariable String appId) {
        return applicationService.removeOne(appId);
    }

    @ApiOperation("添加应用")
    @PostMapping("/add")
    public Integer addOne(@Valid @RequestBody AddApplicationDto applicationDto) {
        return applicationService.add(applicationDto);
    }

    @ApiOperation("应用分配")
    @PostMapping("/allot")
    public Boolean allotApplication(@Valid @RequestBody AllotApplicationDto applicationDto) {
        return applicationService.allot(applicationDto);
    }

    @ApiOperation("根据用户Id查询用户应用列表")
    @GetMapping("/list/{userId}")
    public List<UserApplicationVo> queryUserApplication(@PathVariable Long userId) {
        return applicationService.queryUserApplications(userId);
    }

}

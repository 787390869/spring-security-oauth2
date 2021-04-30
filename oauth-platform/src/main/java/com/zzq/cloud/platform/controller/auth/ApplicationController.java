package com.zzq.cloud.platform.controller.auth;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zzq.cloud.platform.domain.auth.OAuthClientDetail;
import com.zzq.cloud.platform.model.dto.sys.QueryApplicationDto;
import com.zzq.cloud.platform.model.vo.auth.ApplicationVo;
import com.zzq.cloud.platform.model.vo.auth.OAuthClientDetailVo;
import com.zzq.cloud.platform.service.auth.IApplicationService;
import com.zzq.cloud.sdk.base.BaseController;
import com.zzq.cloud.sdk.framework.IOAuthResource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation("应用列表")
    @GetMapping("/list")
    public List<ApplicationVo> queryList(QueryApplicationDto params) {
        return applicationService.queryList(params);
    }

    @ApiOperation("应用列表(分页)")
    @GetMapping("/page")
    public IPage<OAuthClientDetailVo> queryPage(QueryApplicationDto params) {
        params.setUserId(this.getUserId());
        return applicationService.queryPage(params, this.isAdmin());
    }

    @ApiOperation("上架/下架")
    @PutMapping("/{appId}")
    public Integer publish(@PathVariable String appId) {
        return applicationService.publish(appId);
    }
}

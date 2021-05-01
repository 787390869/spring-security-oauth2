package com.zzq.cloud.platform.controller.sys;

import com.zzq.cloud.platform.model.dto.sys.QueryApplicationDto;
import com.zzq.cloud.platform.model.vo.auth.ApplicationVo;
import com.zzq.cloud.platform.model.vo.sys.SysAppGroupVo;
import com.zzq.cloud.platform.service.sys.ISysAppGroupService;
import com.zzq.cloud.sdk.base.BaseController;
import com.zzq.cloud.sdk.framework.IOAuthResource;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ZZQ
 * @date 2021/5/1 13:26
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(IOAuthResource.PATH + "/group")
public class SysAppGroupController extends BaseController {

    private final ISysAppGroupService appGroupService;

    @ApiOperation("获取所有应用组")
    @GetMapping("/list")
    public List<ApplicationVo> list(QueryApplicationDto applicationDto) {
        return appGroupService.findByParams(applicationDto);
    }

    @ApiOperation("应用组列表")
    @GetMapping("/all")
    public List<SysAppGroupVo> all() {
        return appGroupService.findAll();
    }

    @ApiOperation("删除")
    @DeleteMapping("/{groupId}")
    public Integer removeOne(@PathVariable Long groupId) {
        return appGroupService.removeOne(groupId);
    }

}

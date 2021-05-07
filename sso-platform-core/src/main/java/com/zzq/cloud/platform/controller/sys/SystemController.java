package com.zzq.cloud.platform.controller.sys;

import com.zzq.cloud.platform.model.vo.sys.SystemInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZZQ
 * @date 2021/4/29 23:44
 */
@RestController
@Api(tags = {"系统参数控制层"})
@RequestMapping("/system")
@RequiredArgsConstructor
public class SystemController {

    @Value("#{T(Boolean).parseBoolean('${zzq.google:false}')}")
    private Boolean isCheckGoogleCode;

    @Value("#{T(Boolean).parseBoolean('${zzq.smart:false}')}")
    private Boolean isSmartVerify;

    @ApiOperation("获取系统信息")
    @GetMapping("/info")
    public SystemInfoVo systemInfo() {
        return new SystemInfoVo(isCheckGoogleCode, isSmartVerify);
    }

}

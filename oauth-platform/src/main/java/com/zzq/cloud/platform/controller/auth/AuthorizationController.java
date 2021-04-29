package com.zzq.cloud.platform.controller.auth;

import com.zzq.cloud.platform.model.dto.auth.AuthorizationCodeDto;
import com.zzq.cloud.platform.model.vo.auth.AuthorizationVo;
import com.zzq.cloud.platform.service.auth.IAuthService;
import com.zzq.cloud.sdk.annotations.User;
import com.zzq.cloud.sdk.base.BaseController;
import com.zzq.cloud.sdk.base.SecurityUser;
import com.zzq.cloud.sdk.framework.IOAuthResource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

/**
 * @Author ZhangZiQiang
 * @CreateTime 2021/4/29
 */
@Api(tags = {"统一授权控制层"})
@RestController
@RequiredArgsConstructor
@RequestMapping(IOAuthResource.PATH + "/auth")
public class AuthorizationController extends BaseController {

    private final IAuthService authService;

    @ApiOperation("获取授权码")
    @GetMapping("/code")
    public AuthorizationVo code(@Valid AuthorizationCodeDto dto, @User SecurityUser user) {
        dto.setUserId(user.getUserId());
        dto.setUsername(user.getUsername());
        return authService.getAuthorizationCode(dto);
    }

}

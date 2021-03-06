package com.zzq.cloud.platform.controller.auth;

import com.zzq.cloud.platform.model.dto.auth.AuthorizationCodeDto;
import com.zzq.cloud.platform.model.vo.auth.AuthorizationVo;
import com.zzq.cloud.platform.service.auth.IAuthService;
import com.zzq.cloud.sdk.annotations.User;
import com.zzq.cloud.sdk.framework.BaseController;
import com.zzq.cloud.sdk.security.SecurityUser;
import com.zzq.cloud.sdk.framework.IOAuthResource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    private final ConsumerTokenServices consumerTokenServices;

    @ApiOperation("获取授权码")
    @GetMapping("/code")
    public AuthorizationVo code(@Valid AuthorizationCodeDto dto, @User SecurityUser user) {
        dto.setUserId(user.getUserId());
        dto.setUsername(user.getUsername());
        return authService.getAuthorizationCode(dto, this.isAdmin());
    }

    @ApiOperation("登出")
    @PostMapping("/logout")
    public Boolean doLogout() {
        String accessToken = this.getAccessToken();
        if (StringUtils.isNotBlank(accessToken))
            return consumerTokenServices.revokeToken(accessToken);
        return Boolean.FALSE;
    }

}

package com.zzq.cloud.platform.controller.auth;

import com.zzq.cloud.platform.model.dto.auth.CodeLoginDto;
import com.zzq.cloud.platform.model.dto.auth.LoginDto;
import com.zzq.cloud.platform.model.dto.auth.RefreshDto;
import com.zzq.cloud.platform.service.auth.IAuthService;
import com.zzq.cloud.platform.service.auth.IGoogleCodeService;
import com.zzq.cloud.platform.framework.IgnoreResponseBodyAdvice;
import com.zzq.cloud.sdk.framework.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Map;

/**
 * @author ZZQ
 * @date 2021/1/25 18:34
 */
@Api(tags = { "统一授权控制层" })
@RestController
@RequestMapping("/auth")
@SessionAttributes("authorizationRequest")
@RequiredArgsConstructor
public class UnionAuthController extends BaseController {

    private final IAuthService authService;
    private final IGoogleCodeService googleCodeService;

    @ApiOperation("密码模式")
    @PostMapping("/login")
    @IgnoreResponseBodyAdvice
    public Map<String, Object> doLogin(@Valid @RequestBody LoginDto loginDto) {
        return authService.doLogin(loginDto);
    }

    @ApiOperation("应用授权模式")
    @PostMapping("/code_login")
    @IgnoreResponseBodyAdvice
    public Map<String, Object> doCodeLogin(@Valid @RequestBody CodeLoginDto codeLoginDto) {
        return authService.doCodeLogin(codeLoginDto);
    }

    @ApiOperation("刷新凭证")
    @PostMapping("/refresh")
    @IgnoreResponseBodyAdvice
    public Map<String, Object> doRefresh(@Valid RefreshDto refreshDto) {
        return authService.doRefresh(refreshDto);
    }

    @ApiOperation("获取googleKey")
    @GetMapping("/google_key")
    public String genKey(String username) {
        return googleCodeService.genKey(username);
    }
}

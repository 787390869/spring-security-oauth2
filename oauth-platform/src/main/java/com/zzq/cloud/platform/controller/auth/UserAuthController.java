package com.zzq.cloud.platform.controller.auth;

import com.zzq.cloud.platform.model.dto.auth.LoginDto;
import com.zzq.cloud.platform.service.auth.IAuthService;
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
public class UserAuthController {

    private final IAuthService authService;

    @ApiOperation("登录(token获取)")
    @PostMapping("/login")
    public Map<String, Object> doLogin(@Valid LoginDto loginDto) {
        return authService.doLogin(loginDto);
    }

}

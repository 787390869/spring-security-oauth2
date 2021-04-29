package com.zzq.cloud.platform.model.vo.auth;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author ZhangZiQiang
 * @CreateTime 2021/4/29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorizationVo {

    @ApiModelProperty("授权码")
    private String authorizationCode;

}

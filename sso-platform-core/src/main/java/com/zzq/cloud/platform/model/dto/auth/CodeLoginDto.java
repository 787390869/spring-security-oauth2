package com.zzq.cloud.platform.model.dto.auth;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @Author ZhangZiQiang
 * @CreateTime 2021/4/29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CodeLoginDto {

    @NotBlank(message = "应用标识不能为空")
    @ApiModelProperty(value = "appId", required = true)
    private String appId;

    @NotBlank(message = "授权码不能为空")
    @ApiModelProperty("授权码")
    private String authorizationCode;

}

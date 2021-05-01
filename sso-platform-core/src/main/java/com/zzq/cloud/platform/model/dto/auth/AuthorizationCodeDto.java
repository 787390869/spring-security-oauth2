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
public class AuthorizationCodeDto {

    @NotBlank(message = "无效的AppID")
    @ApiModelProperty(value = "平台id", example = "client", required = true)
    private String appId;

    @ApiModelProperty(value = "用户Id", hidden = true)
    private Long userId;

    @ApiModelProperty(value = "用户名", hidden = true)
    private String username;

}

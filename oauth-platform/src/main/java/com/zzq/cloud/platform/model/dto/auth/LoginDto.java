package com.zzq.cloud.platform.model.dto.auth;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author ZZQ
 * @date 2021/1/25 18:36
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {

    @NotBlank(message = "无效的AppID")
    @ApiModelProperty(value = "平台id", example = "client", required = true)
    private String appId;

    @NotBlank(message = "无效的AppSecret")
    @ApiModelProperty(value = "平台秘钥", example = "secret", required = true)
    private String appSecret;

    @NotNull(message = "谷歌验证码不能为空")
    @ApiModelProperty("谷歌验证码")
    private Integer googleCode;

    @NotBlank(message = "无效的用户名")
    @ApiModelProperty(value = "平台秘钥", example = "admin", required = true)
    private String username;

    @NotBlank(message = "无效的密码")
    @ApiModelProperty(value = "平台秘钥", example = "wowangle", required = true)
    private String password;

}

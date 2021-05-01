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
public class RefreshDto {

    @NotBlank(message = "应用标识不能为空")
    @ApiModelProperty(value = "appId")
    private String appId;

    @NotBlank(message = "应用秘钥不能为空")
    @ApiModelProperty("appSecret")
    private String appSecret;

    @NotBlank(message = "刷新令牌不能为空")
    @ApiModelProperty("刷新令牌")
    private String refreshToken;

}

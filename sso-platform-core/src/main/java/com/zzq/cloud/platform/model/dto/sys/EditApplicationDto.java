package com.zzq.cloud.platform.model.dto.sys;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author ZZQ
 * @date 2021/5/1 1:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditApplicationDto {

    @NotBlank(message = "应用id不能为空")
    @ApiModelProperty("应用id")
    private String clientId;

    @ApiModelProperty("秘钥")
    private String originalSecret;

    @ApiModelProperty("应用图标")
    private String icon;

    @ApiModelProperty("客户端名称")
    private String clientName;

    @ApiModelProperty("应用组号")
    private Long groupId;

    @ApiModelProperty("访问令牌过期时间")
    private Integer accessTokenValidity;

    @ApiModelProperty("刷新令牌过期时间")
    private Integer refreshTokenValidity;

    @ApiModelProperty("授权跳转地址")
    private String webServerRedirectUri;

    @NotNull(message = "授权模式不能为空")
    @ApiModelProperty("授权模式")
    private List<String> grantTypes;

}

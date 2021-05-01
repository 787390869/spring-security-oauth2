package com.zzq.cloud.platform.model.dto.sys;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * @author ZZQ
 * @date 2021/5/1 2:15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddApplicationDto {

    @NotBlank(message = "客户端名称不能为空")
    @ApiModelProperty("客户端名称")
    private String clientName;

    @NotNull(message = "应用组号不能为空")
    @ApiModelProperty("应用组号")
    private Long groupId;

    @NotBlank(message = "客户端秘钥不能为空")
    @ApiModelProperty("客户端原秘钥")
    private String originalSecret;

    @NotBlank(message = "授权范围不能为空")
    @ApiModelProperty("授权范围")
    private  String scope;

    @NotBlank(message = "应用图标不能为空")
    @ApiModelProperty("图标")
    private String icon;

    @ApiModelProperty(value = "授权模式", hidden = true)
    private String authorizedGrantTypes;

    @NotNull(message = "授权模式不能为空")
    @ApiModelProperty("授权模式")
    private List<String> grantTypes;

    @NotBlank(message = "授权跳转地址不能为空")
    @ApiModelProperty("授权跳转地址")
    private String webServerRedirectUri;

    @Pattern(regexp = "\\d{3,8}", message = "访问令牌时效3-8位数字")
    @NotNull(message = "访问令牌有效期不能为空")
    @ApiModelProperty("访问令牌过期时间")
    private String accessTokenValidity;

    @Pattern(regexp = "\\d{3,8}", message = "刷新令牌时效3-8位数字")
    @ApiModelProperty("刷新令牌过期时间")
    private String refreshTokenValidity;

    @ApiModelProperty("附加信息")
    private String additionalInformation;

}

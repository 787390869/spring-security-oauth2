package com.zzq.cloud.platform.model.vo.auth;

import com.zzq.cloud.platform.model.vo.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

/**
 * 应用Vo oauth_client_details
 * @author ZZQ
 * @date 2021-04-29
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "OauthClientDetailsVo", description = "应用")
public class OAuthClientDetailVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("客户端标识")
    private String clientId;

    @ApiModelProperty("客户端秘钥")
    private String originalSecret;

    @ApiModelProperty("客户端名称")
    private String clientName;

    @ApiModelProperty("组号")
    private Long groupId;

    @ApiModelProperty("应用组名")
    private String groupName;

    @ApiModelProperty("资源id")
    private String resourceIds;

    @ApiModelProperty("授权模式")
    private String authorizedGrantTypes;

    @ApiModelProperty("图标")
    private String icon;

    @ApiModelProperty("授权跳转地址")
    private String webServerRedirectUri;

    @ApiModelProperty("凭证")
    private String authorities;

    @ApiModelProperty("访问令牌过期时间")
    private Integer accessTokenValidity;

    @ApiModelProperty("刷新令牌过期时间")
    private Integer refreshTokenValidity;

    @ApiModelProperty("附加信息")
    private String additionalInformation;

    @ApiModelProperty("自动授权")
    private Boolean autoapprove;

    @ApiModelProperty("授权模式")
    private List<String> grantTypes;

    @ApiModelProperty("发布状态")
    private Integer isPublish;

    @ApiModelProperty("是否支持授权码模式")
    private Boolean isAuth;

}
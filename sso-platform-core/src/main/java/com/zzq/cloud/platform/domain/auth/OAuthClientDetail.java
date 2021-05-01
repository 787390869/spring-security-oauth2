package com.zzq.cloud.platform.domain.auth;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zzq.cloud.platform.domain.BaseEntity;
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
@TableName(value = "oauth_client_details")
public class OAuthClientDetail extends BaseEntity {

    @TableId(type = IdType.NONE)
    @ApiModelProperty("客户端标识")
    private String clientId;

    @ApiModelProperty("客户端名称")
    private String clientName;

    @ApiModelProperty("应用组号")
    private Long groupId;

    @ApiModelProperty("资源id")
    private String resourceIds;

    @ApiModelProperty("客户端秘钥")
    private String clientSecret;

    @ApiModelProperty("客户端原秘钥")
    private String originalSecret;

    @ApiModelProperty("授权范围")
    private  String scope;

    @ApiModelProperty("图标")
    private String icon;

    @ApiModelProperty("授权模式")
    private String authorizedGrantTypes;

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

    @ApiModelProperty("上架状态")
    private Integer isPublish;


}

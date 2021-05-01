package com.zzq.cloud.platform.model.vo.sys;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 权限Vo sys_permission
 * @author ZZQ
 * @date 2021-01-25
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "SysPermissionVo", description = "权限")
public class SysPermissionVo {

    private static final long serialVersionUID = 1L;

    /** 权限id */
    private Long perId;

    /** 权限标识 */
    @ApiModelProperty(value = "权限标识")
    private String perm;

    /** 权限名称 */
    @ApiModelProperty(value = "权限名称")
    private String perName;

    /** 权限描述 */
    @ApiModelProperty(value = "权限描述")
    private String perDesc;

    /** 左值 */
    @ApiModelProperty(value = "左值")
    private Long leftValue;

    /** 父权限id */
    @ApiModelProperty(value = "父权限id")
    private Long parentId;

    /** 右值 */
    @ApiModelProperty(value = "右值")
    private Long rightValue;

    /** 层级 */
    @ApiModelProperty(value = "层级")
    private Long layer;

    /** 显示顺序 */
    @ApiModelProperty(value = "显示顺序")
    private Long sort;

    /** 图标 */
    @ApiModelProperty(value = "图标")
    private String icon;

    /** 路由地址 */
    @ApiModelProperty(value = "路由地址")
    private String routePath;

    /** 组件路径 */
    @ApiModelProperty(value = "组件路径")
    private String componentPath;

    /** 0-不外链 1-外链 */
    @ApiModelProperty(value = "0-不外链 1-外链")
    private Integer isFrame;

    /** 0-不缓存 1-缓存 */
    @ApiModelProperty(value = "0-不缓存 1-缓存")
    private Integer isCache;

    /** 1-目录 2-菜单 3-按钮 */
    @ApiModelProperty(value = "1-目录 2-菜单 3-按钮")
    private Integer type;

    /** 平台标识 */
    @ApiModelProperty(value = "平台标识")
    private Long platform;

    @Override
    public String toString(){
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("perId",getPerId())
                .append("perm",getPerm())
                .append("perName",getPerName())
                .append("perDesc",getPerDesc())
                .append("leftValue",getLeftValue())
                .append("parentId",getParentId())
                .append("rightValue",getRightValue())
                .append("layer",getLayer())
                .append("sort",getSort())
                .append("icon",getIcon())
                .append("routePath",getRoutePath())
                .append("componentPath",getComponentPath())
                .append("isFrame",getIsFrame())
                .append("isCache",getIsCache())
                .append("type",getType())
                .append("platform",getPlatform())
                .toString();
    }
}

package com.zzq.cloud.platform.model.dto.sys;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 权限AddDTO sys_permission
 * @author ZZQ
 * @date 2021-01-25
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "AddSysPermissionDto", description = "权限")
public class AddSysPermissionDto {

    private static final long serialVersionUID = 1L;

    /** 权限标识 */
    @NotBlank(message = "权限标识不能为空")
    @ApiModelProperty(value = "权限标识", required = true)
    private String perm;

    /** 权限名称 */
    @NotBlank(message = "权限名称不能为空")
    @ApiModelProperty(value = "权限名称", required = true)
    private String perName;

    /** 权限描述 */
    @ApiModelProperty(value = "权限描述")
    private String perDesc;

    /** 左值 */
    @ApiModelProperty(value = "左值", hidden = true)
    private Long leftValue;

    /** 父权限id */
    @NotNull(message = "父权限id不能为空")
    @ApiModelProperty(value = "父权限id", required = true)
    private Long parentId;

    /** 右值 */
    @ApiModelProperty(value = "右值", hidden = true)
    private Long rightValue;

    /** 层级 */
    @ApiModelProperty(value = "层级", hidden = true)
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
    @ApiModelProperty(value = "1-目录 2-菜单 3-按钮(默认3)")
    private Integer type;

    /** 平台标识 */
    @NotNull(message = "平台标识不能为空")
    @ApiModelProperty(value = "平台标识", required = true)
    private Long platform;

}

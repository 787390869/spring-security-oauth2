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
 * 角色权限Vo sys_role_permission
 * @author ZZQ
 * @date 2021-01-25
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "SysRolePermissionVo", description = "角色权限")
public class SysRolePermissionVo {

    private static final long serialVersionUID = 1L;

    /** 角色权限id */
    private Long id;

    /** 角色id */
    @ApiModelProperty(value = "角色id")
    private Long roleId;

    /** 权限id */
    @ApiModelProperty(value = "权限id")
    private Long permissionId;

    @Override
    public String toString(){
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id",getId())
                .append("roleId",getRoleId())
                .append("permissionId",getPermissionId())
                .toString();
    }
}

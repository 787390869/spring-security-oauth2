package com.zzq.cloud.platform.domain.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zzq.cloud.platform.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 角色权限 sys_role_permission
 * @author ZZQ
 * @date 2021-01-25
 */
@Data
@Builder
@TableName(value = "sys_role_permission")
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "sys_role_permission", description = "角色权限")
public class SysRolePermission extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** 角色权限id */
    @TableId(type = IdType.ASSIGN_ID)
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

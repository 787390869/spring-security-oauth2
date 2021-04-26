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
 * 角色Vo sys_role
 * @author ZZQ
 * @date 2021-01-25
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "SysRoleVo", description = "角色")
public class SysRoleVo {

    private static final long serialVersionUID = 1L;

    /** 角色id */
    private Long roleId;

    /** 角色标识 */
    @ApiModelProperty(value = "角色标识")
    private String role;

    /** 角色名称 */
    @ApiModelProperty(value = "角色名称")
    private String roleName;

    /** 角色描述 */
    @ApiModelProperty(value = "角色描述")
    private String roleDesc;

    /** 显示顺序 */
    @ApiModelProperty(value = "显示顺序")
    private Long sort;

    /** 0-未启用 1-已启用 */
    @ApiModelProperty(value = "0-未启用 1-已启用")
    private Integer state;

    @Override
    public String toString(){
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("roleId",getRoleId())
                .append("role",getRole())
                .append("roleName",getRoleName())
                .append("roleDesc",getRoleDesc())
                .append("sort",getSort())
                .append("state",getState())
                .toString();
    }
}

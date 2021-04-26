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
 * 用户角色Vo sys_user_role
 * @author ZZQ
 * @date 2021-01-25
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "SysUserRoleVo", description = "用户角色")
public class SysUserRoleVo {

    private static final long serialVersionUID = 1L;

    /** 用户-角色id */
    private Long id;

    /** 用户id */
    @ApiModelProperty(value = "用户id")
    private Long userId;

    /** 角色id */
    @ApiModelProperty(value = "角色id")
    private Long roleId;

    @Override
    public String toString(){
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id",getId())
                .append("userId",getUserId())
                .append("roleId",getRoleId())
                .toString();
    }
}

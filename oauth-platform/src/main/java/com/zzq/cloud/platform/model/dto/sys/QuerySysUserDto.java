package com.zzq.cloud.platform.model.dto.sys;

import com.zzq.cloud.platform.model.dto.BasePageDto;
import com.zzq.cloud.platform.model.enums.UserStateEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户QueryDTO sys_user
 * @author ZZQ
 * @date 2021-01-25
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "QuerySysUserDto", description = "用户")
public class QuerySysUserDto extends BasePageDto<QuerySysUserDto> {

    private static final long serialVersionUID = 1L;

    /** 部门id */
    @ApiModelProperty(value = "部门id")
    private Long deptId;

    /** 用户账户 */
    @ApiModelProperty(value = "用户账户/昵称")
    private String name;

    /** 用户手机号 */
    @ApiModelProperty(value = "用户手机号")
    private String phone;

    @ApiModelProperty(value = "用户角色")
    private String role;

    @ApiModelProperty(value = "启用状态 0-禁用 1-启用 -1-全部")
    private UserStateEnum state;

}

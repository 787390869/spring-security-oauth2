package com.zzq.cloud.platform.model.dto.sys;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 用户AddDTO sys_user
 * @author ZZQ
 * @date 2021-01-25
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "AddSysUserDto", description = "用户")
public class AddSysUserDto {

    private static final long serialVersionUID = 1L;

    /** 部门id */
    @ApiModelProperty(value = "部门id")
    private Long deptId;

    /** 用户账户 */
    @NotBlank(message = "用户账户不能为空")
    @ApiModelProperty(value = "用户账户", required = true)
    private String username;

    /** 密码 */
    @NotBlank(message = "用户密码不能为空")
    @ApiModelProperty(value = "密码", required = true)
    private String password;

    /** 用户手机号 */
    @NotBlank(message = "用户手机号码不能为空")
    @ApiModelProperty(value = "用户手机号", required = true)
    private String phone;

    /** 用户昵称 */
    @ApiModelProperty(value = "用户昵称")
    private String nickname;

    /** 0-未知 1-男 2-女 */
    @ApiModelProperty(value = "0-未知 1-男 2-女")
    private Integer sex;

    /** 头像地址 */
    @ApiModelProperty(value = "头像地址")
    private String avator;

    /** 邮箱 */
    @ApiModelProperty(value = "邮箱")
    private String email;

    /** 启用状态 0-未启用 1-已启用 */
    @ApiModelProperty(value = "启用状态 0-未启用 1-已启用")
    private Integer state;

    @NotNull(message = "用户酒店集合不能为空")
    @ApiModelProperty(value = "用户酒店Ids", example = "[1,2,3]", required = true)
    private List<Long> hotelIds;


}

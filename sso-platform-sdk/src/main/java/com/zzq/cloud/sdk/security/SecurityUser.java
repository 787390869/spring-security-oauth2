package com.zzq.cloud.sdk.security;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

/**
 * @author ZZQ
 * @date 2021/1/25 17:21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SecurityUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户Id", hidden = true)
    private Long userId;

    @ApiModelProperty(value = "部门id", hidden = true)
    private Long deptId;

    @ApiModelProperty(value = "用户账户", hidden = true)
    private String username;

    @ApiModelProperty(value = "用户手机号", hidden = true)
    private String phone;

    @ApiModelProperty(value = "用户昵称", hidden = true)
    private String nickname;

    @ApiModelProperty(value = "0-未知 1-男 2-女", hidden = true)
    private Integer sex;

    @ApiModelProperty(value = "头像地址", hidden = true)
    private String avator;

    @ApiModelProperty(value = "邮箱", hidden = true)
    private String email;

    @ApiModelProperty(value = "启用状态 0-未启用 1-已启用", hidden = true)
    private Integer state;

    @ApiModelProperty(value = "角色列表", hidden = true)
    private Set<String> roles;

    @ApiModelProperty(value = "权限列表", hidden = true)
    private Set<String> permissions;

}

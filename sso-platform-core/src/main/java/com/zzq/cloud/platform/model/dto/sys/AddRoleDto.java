package com.zzq.cloud.platform.model.dto.sys;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AddRoleDto {

    @NotBlank(message = "角色不能为空")
    private String role;

    /**
     * 角色中文名
     */
    @NotBlank(message = "角色名称不能为空")
    private String roleName;

    /**
     * 角色描述
     */
    private String roleDesc;

    @ApiModelProperty("平台标识")
    private String clientId;

}

package com.zzq.cloud.platform.model.dto.sys;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class EditRoleDto {

    @NotNull(message = "角色Id不能为空")
    private Long roleId;

    /**
     * 角色中文名
     */
    @NotBlank(message = "角色名称不能为空")
    private String roleName;

    /**
     * 角色描述
     */
    private String roleDesc;

}

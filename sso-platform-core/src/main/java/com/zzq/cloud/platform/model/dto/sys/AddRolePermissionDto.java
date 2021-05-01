package com.zzq.cloud.platform.model.dto.sys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddRolePermissionDto {

    @NotNull(message = "角色id不能为空")
    private Long roleId;

    @NotNull(message = "权限不能为空")
    private String permissionIds;

}

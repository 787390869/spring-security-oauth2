package com.zzq.cloud.platform.model.dto.sys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author ZZQ
 * @date 2021/5/1 18:59
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditPermissionDto {

    @NotNull(message = "权限id不能为空")
    private Long perId;

    private Long parentId;

    @NotBlank(message = "权限不能为空")
    private String perm;

    @NotBlank(message = "权限名称不能为空")
    private String perName;

    private String perDesc;

    @NotNull(message = "权限类型不能为空")
    private Integer type;

}

package com.zzq.cloud.platform.model.vo.sys;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PermissionVo {

    private Long perId;

    private String clientId;

    @ApiModelProperty("平台名称")
    private String clientName;

    /**
     * 父权限id
     */
    private Long parentId;

    /**
     * 父权限名
     */
    private String parentPerm;

    /**
     * 权限英文名
     */
    private String perm;

    /**
     * 权限中文名
     */
    private String perName;

    /**
     * 权限描述
     */
    private String perDesc;

    /**
     * 权限类型
     */
    private Integer type;

    /**
     * 子权限
     */
    private List<PermissionVo> children;

}

package com.zzq.cloud.platform.model.vo.sys;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author ZZQ
 * @date 2021/5/1 20:13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PermissionTreeVo {

    @ApiModelProperty("权限id集合")
    private List<Long> perIds;

    @ApiModelProperty("权限树")
    private List<PermissionVo> permissions;

}

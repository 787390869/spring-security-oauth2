package com.zzq.cloud.platform.model.vo.sys;

import com.zzq.cloud.platform.model.vo.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * 应用分组Vo sys_app_group
 * @author ZZQ
 * @date 2021-04-30
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "SysAppGroupVo", description = "应用分组")
public class SysAppGroupVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    /** 应用分组id */
    private Long id;

    /** 组名 */
    @ApiModelProperty(value = "组名")
    private String groupName;

}

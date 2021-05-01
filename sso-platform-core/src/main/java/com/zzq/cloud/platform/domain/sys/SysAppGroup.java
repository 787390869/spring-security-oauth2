package com.zzq.cloud.platform.domain.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zzq.cloud.platform.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * 应用分组 sys_app_group
 * @author ZZQ
 * @date 2021-04-30
 */
@Data
@Builder
@TableName(value = "sys_app_group")
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SysAppGroup", description = "应用分组")
public class SysAppGroup extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** 应用分组id */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /** 组名 */
    @ApiModelProperty(value = "组名")
    private String groupName;

}
package com.zzq.cloud.platform.domain.auth;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zzq.cloud.platform.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * 用户应用 sys_user_application
 * @author ZZQ
 * @date 2021-04-29
 */
@Data
@Builder
@TableName(value = "sys_user_application")
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "UserApplication", description = "用户应用")
public class UserApplication extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** 用户-应用id */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /** 用户id */
    @ApiModelProperty(value = "用户id")
    private Long userId;

    /** 应用id */
    @ApiModelProperty(value = "应用id")
    private String clientId;

}
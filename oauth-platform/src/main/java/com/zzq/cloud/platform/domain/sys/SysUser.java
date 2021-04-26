package com.zzq.cloud.platform.domain.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zzq.cloud.platform.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 用户 sys_user
 * @author ZZQ
 * @date 2021-01-25
 */
@Data
@Builder
@TableName(value = "sys_user")
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "sys_user", description = "用户")
public class SysUser extends BaseEntity {

    private static final long serialVersionUID = 1L;

    public static final Long  BASE_USER_ID = 1L;

    /** 用户id */
    @TableId(type = IdType.ASSIGN_ID)
    private Long userId;

    /** 部门id */
    @ApiModelProperty(value = "部门id")
    private Long deptId;

    /** 用户账户 */
    @ApiModelProperty(value = "用户账户")
    private String username;

    /** 密码 */
    @ApiModelProperty(value = "密码")
    private String password;

    /** 用户手机号 */
    @ApiModelProperty(value = "用户手机号")
    private String phone;

    /** 用户昵称 */
    @ApiModelProperty(value = "用户昵称")
    private String nickname;

    /** 0-未知 1-男 2-女 */
    @ApiModelProperty(value = "0-未知 1-男 2-女")
    private Integer sex;

    /** 头像地址 */
    @ApiModelProperty(value = "头像地址")
    private String avator;

    /** 邮箱 */
    @ApiModelProperty(value = "邮箱")
    private String email;

    /** 启用状态 0-未启用 1-已启用 */
    @ApiModelProperty(value = "启用状态 0-未启用 1-已启用")
    private Integer state;

    @Override
    public String toString(){
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("userId",getUserId())
            .append("deptId",getDeptId())
            .append("username",getUsername())
            .append("password",getPassword())
            .append("phone",getPhone())
            .append("nickname",getNickname())
            .append("sex",getSex())
            .append("avator",getAvator())
            .append("email",getEmail())
            .append("state",getState())
            .toString();
    }
}

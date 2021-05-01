package com.zzq.cloud.platform.model.vo.sys;

import com.zzq.cloud.platform.model.vo.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 用户Vo sys_user
 * @author ZZQ
 * @date 2021-01-25
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "SysUserVo", description = "用户")
public class SysUserVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    /** 用户id */
    private Long userId;

    /** 部门id */
    @ApiModelProperty(value = "部门id")
    private Long deptId;

    /** 用户账户 */
    @ApiModelProperty(value = "用户账户")
    private String username;

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

    @ApiModelProperty("谷歌key")
    private String googleKey;

    @ApiModelProperty("角色")
    private List<String> roles;

}

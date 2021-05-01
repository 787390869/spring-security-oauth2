package com.zzq.cloud.platform.model.dto.sys;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class EditUserDto {

    @ApiModelProperty("头像")
    private String avator;

    @NotNull(message = "用户Id不能为空")
    private Long userId;

    private String nickname;

    private String phone;

    @NotNull(message = "用户角色不能为空")
    private List<String> roles;

}

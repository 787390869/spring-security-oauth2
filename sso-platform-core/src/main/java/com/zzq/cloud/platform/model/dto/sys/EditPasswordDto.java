package com.zzq.cloud.platform.model.dto.sys;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class EditPasswordDto {

    @NotNull(message = "用户ID不能为空")
    private Long userId;

    @Length(min = 6, max = 10, message = "密码长度6-10位")
    @NotBlank(message = "密码不能为空")
    private String password;

}

package com.zzq.cloud.platform.model.dto.sys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author ZZQ
 * @date 2021/5/4 17:17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditGroupDto {

    @NotNull(message = "应用id不能为空")
    private Long id;

    private String groupName;

}

package com.zzq.cloud.platform.model.dto.sys;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author ZZQ
 * @date 2021/5/4 18:20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AllotApplicationDto {

    @NotNull(message = "用户id不能为空")
    @ApiModelProperty("用户id")
    private Long userId;

    @NotNull(message = "应用列表不能为空")
    @ApiModelProperty("应用列表")
    private List<String> appIds;

}

package com.zzq.cloud.platform.model.vo.sys;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ZZQ
 * @date 2021/5/4 18:39
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserApplicationVo {

    @ApiModelProperty("应用标识")
    private String clientId;

    @ApiModelProperty("应用名称")
    private String clientName;

}

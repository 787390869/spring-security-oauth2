package com.zzq.cloud.platform.model.dto.sys;

import com.zzq.cloud.platform.model.dto.BasePageDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ZZQ
 * @date 2021/5/1 21:05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryPermissionDto extends BasePageDto<QueryPermissionDto> {

    @ApiModelProperty("应用标识")
    private String clientId;

    private String perm;

    private String perName;

    private Integer type;

}

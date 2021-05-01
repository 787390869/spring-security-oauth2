package com.zzq.cloud.platform.model.dto.sys;

import com.zzq.cloud.platform.model.dto.BasePageDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author ZhangZiQiang
 * @CreateTime 2021/4/30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryApplicationDto extends BasePageDto<QueryApplicationDto> {

    @ApiModelProperty("应用名称")
    private String clientName;

    @ApiModelProperty("上架状态")
    private Integer isPublish;

    @ApiModelProperty(value = "用户id", hidden = true)
    private Long userId;

    @ApiModelProperty(value = "用户名", hidden = true)
    private String username;

    @ApiModelProperty(value = "用户组名称")
    private String groupName;

}

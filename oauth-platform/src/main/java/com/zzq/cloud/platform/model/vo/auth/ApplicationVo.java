package com.zzq.cloud.platform.model.vo.auth;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author ZhangZiQiang
 * @CreateTime 2021/4/30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationVo {

    @ApiModelProperty("应用组号")
    private Long id;

    @ApiModelProperty("应用组名")
    private String groupName;

    @ApiModelProperty("应用列表")
    private List<OAuthClientDetailVo> apps;

}

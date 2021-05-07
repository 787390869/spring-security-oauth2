package com.zzq.cloud.platform.model.dto.auth;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author ZhangZiQiang
 * @CreateTime 2021/5/7
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SmartVerifyDto {

    @ApiModelProperty("回话id")
    private String sessionId;

    @ApiModelProperty("签名")
    private String sig;

    @ApiModelProperty("智能验证token")
    private String token;

    @ApiModelProperty("智能验证场景")
    private String scene;

}

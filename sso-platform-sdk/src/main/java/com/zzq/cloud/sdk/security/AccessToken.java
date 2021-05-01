package com.zzq.cloud.sdk.security;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author ZhangZiQiang
 * @CreateTime 2021/4/29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccessToken {

    private String access_token;
    private String refresh_token;
    private String token_type;
    private Integer expires_in;
    private String scope;

}

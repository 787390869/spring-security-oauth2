package com.zzq.cloud.platform.model.vo.sys;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ZZQ
 * @date 2021/4/29 23:45
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SystemInfoVo {

    @ApiModelProperty("是否鉴定谷歌code")
    private Boolean isCheckGoogleCode;

    @ApiModelProperty("是否智能验证")
    private Boolean isSmartVerify;

}

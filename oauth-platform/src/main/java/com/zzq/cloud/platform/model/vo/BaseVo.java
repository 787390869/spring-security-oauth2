package com.zzq.cloud.platform.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author ZZQ
 * @date 2021/1/26 9:47
 */
@Data
public class BaseVo {

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "0-未删除 1-已删除")
    private Integer isDelete;

}

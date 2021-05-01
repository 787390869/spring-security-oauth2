package com.zzq.cloud.platform.model.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author ZhangZiQiang
 * @Date 2021/3/16 14:59
 **/
@Data
@ApiModel(value = "BasePageDto", description = "分页参数")
public class BasePageDto<T> {

    @ApiModelProperty("当前页码(默认1)")
    private Integer pageNum;

    @ApiModelProperty("每一页数量(默认10)")
    private Integer pageSize;

    public BasePageDto() {
        this.pageNum = 1;
        this.pageSize = 10;
    }

    public BasePageDto(Integer pageNum, Integer pageSize) {
        if (null == pageNum) {
            this.pageNum = 1;
        }
        if (null == pageSize) {
            this.pageSize = 10;
        }
    }

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public Page<T> page() {
        return new Page<>(this.pageNum, this.pageSize);
    }

}

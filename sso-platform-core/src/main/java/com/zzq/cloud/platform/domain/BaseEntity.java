package com.zzq.cloud.platform.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BaseEntity implements Serializable {

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间", hidden = true)
    private Date createTime;

    @TableField(value = "creator", fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建者", hidden = true)
    private String creator;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "修改时间", hidden = true)
    private Date updateTime;

    @TableField(value = "updator", fill = FieldFill.UPDATE)
    @ApiModelProperty(value = "修改者", hidden = true)
    private String updator;

    @TableField(value = "is_delete")
    @ApiModelProperty(value = "0-未删除 1-已删除", hidden = true)
    private Integer isDelete;

}

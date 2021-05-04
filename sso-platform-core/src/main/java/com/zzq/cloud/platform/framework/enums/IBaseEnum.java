package com.zzq.cloud.platform.framework.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author ZZQ
 * @date 2020/12/21 22:24
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public interface IBaseEnum<T> {

    Integer getCode();

}


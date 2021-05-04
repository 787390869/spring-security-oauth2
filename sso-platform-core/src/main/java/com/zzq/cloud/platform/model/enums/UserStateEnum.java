package com.zzq.cloud.platform.model.enums;

import com.zzq.cloud.platform.framework.enums.IBaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author ZZQ
 * @date 2021/1/26 9:53
 */
@Getter
@AllArgsConstructor
public enum UserStateEnum implements IBaseEnum {

    ALL(-1, "全部"),
    OFF(0, "禁用"),
    ON(1, "启用");

    private final Integer code;

    private final String state;

}

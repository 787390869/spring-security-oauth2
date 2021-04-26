package com.zzq.cloud.platform.model.enums;

import com.zzq.cloud.sdk.framework.enums.IBaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author ZZQ
 * @date 2021/1/26 9:50
 */
@Getter
@AllArgsConstructor
public enum UserSexEnum implements IBaseEnum {

    UN_KNOWN(0, "未知"),
    MALE(1, "男"),
    FEMALE(2, "女")
    ;

    private final Integer code;

    private final String sex;

}

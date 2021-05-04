package com.zzq.cloud.platform.model.enums;

import com.zzq.cloud.platform.framework.enums.IBaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author ZZQ
 * @date 2021/5/1 0:10
 */
@Getter
@AllArgsConstructor
public enum GrantTypeEnum implements IBaseEnum<GrantTypeEnum> {

    PASSWORD(1, "password"),
    AUTHORIZation_code(2, "authorization_code"),
    REFRESH(3, "refresh_token")
    ;

    private final Integer code;
    private final String grantType;
}

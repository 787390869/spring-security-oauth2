package com.zzq.cloud.platform.model.dto.sys;

import com.zzq.cloud.platform.model.dto.BasePageDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

@Data
@EqualsAndHashCode(callSuper = true)
public class QueryRoleDto extends BasePageDto<QueryRoleDto> {

    private String role;

    private Integer state;

    private String permission;

    private String clientId;

}

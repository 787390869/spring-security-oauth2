package com.zzq.cloud.platform.service.auth;

import com.zzq.cloud.platform.model.dto.auth.LoginDto;

/**
 * @Author ZhangZiQiang
 * @CreateTime 2021/5/7
 */
public interface IAliYunService {

    Boolean doSmartVerify(LoginDto loginDto);

}

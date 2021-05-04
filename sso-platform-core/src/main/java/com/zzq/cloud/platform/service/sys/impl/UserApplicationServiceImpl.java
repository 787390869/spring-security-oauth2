package com.zzq.cloud.platform.service.sys.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzq.cloud.platform.domain.auth.UserApplication;
import com.zzq.cloud.platform.mapper.auth.UserApplicationMapper;
import com.zzq.cloud.platform.service.sys.IUserApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author ZZQ
 * @date 2021/5/4 18:24
 */
@Service
@RequiredArgsConstructor
public class UserApplicationServiceImpl extends ServiceImpl<UserApplicationMapper, UserApplication> implements IUserApplicationService {
}

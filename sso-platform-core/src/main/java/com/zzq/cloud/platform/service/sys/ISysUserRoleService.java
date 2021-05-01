package com.zzq.cloud.platform.service.sys;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zzq.cloud.platform.domain.sys.SysUserRole;

import java.util.List;

/**
 * 用户角色Service接口
 * 
 * @author ZZQ
 * @date 2021-01-25
 */
public interface ISysUserRoleService extends IService<SysUserRole> {

    void removeByUserId(Long userId);

    void saveAll(List<Long> roleIds, Long userId);

    void saveMany(List<String> roles, Long userId);

}

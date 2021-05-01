package com.zzq.cloud.platform.mapper.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzq.cloud.platform.domain.sys.SysRolePermission;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 角色权限Mapper接口
 * @author ZZQ
 * @date 2021-01-25
 */
@Mapper
@Repository
public interface SysRolePermissionMapper extends BaseMapper<SysRolePermission> {

    Long findRemoveId(Long roleId, Long permissionId);

}

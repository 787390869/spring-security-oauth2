package com.zzq.cloud.platform.mapper.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzq.cloud.platform.domain.sys.SysPermission;
import com.zzq.cloud.platform.model.dto.sys.QueryPermissionDto;
import com.zzq.cloud.platform.model.vo.sys.PermissionVo;
import com.zzq.cloud.platform.model.vo.sys.SysPermissionVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * 权限Mapper接口
 * @author ZZQ
 * @date 2021-01-25
 */
@Mapper
@Repository
public interface SysPermissionMapper extends BaseMapper<SysPermission> {

    void callInsert(SysPermission permission);

    SysPermissionVo findById(Long permissionId);

    List<PermissionVo> findByClientId(String clientId);

    IPage<PermissionVo> findByParams(Page<?> page, @Param("p") QueryPermissionDto params);

    List<Long> findByRoleId(Long roleId);

    int isExistOtherClientPermission(@Param("clientId") String clientId, @Param("permissionIds") Set<Long> permissionIds);
}

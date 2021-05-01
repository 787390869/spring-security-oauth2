package com.zzq.cloud.platform.service.sys;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzq.cloud.platform.domain.sys.SysRole;
import com.zzq.cloud.platform.model.dto.sys.AddRoleDto;
import com.zzq.cloud.platform.model.dto.sys.EditRoleDto;
import com.zzq.cloud.platform.model.dto.sys.QueryRoleDto;
import com.zzq.cloud.platform.model.vo.sys.SysRoleVo;

import java.util.List;

/**
 * 角色Service接口
 * 
 * @author ZZQ
 * @date 2021-01-25
 */
public interface ISysRoleService extends IService<SysRole> {

    IPage<SysRoleVo> queryPage(QueryRoleDto roleDto);

    List<String> queryList();

    Integer state(Long roleId);

    Integer removeOne(Long roleId);

    Integer addRole(AddRoleDto roleDto);

    Integer edit(EditRoleDto roleDto);

    List<SysRoleVo> allRole();
}

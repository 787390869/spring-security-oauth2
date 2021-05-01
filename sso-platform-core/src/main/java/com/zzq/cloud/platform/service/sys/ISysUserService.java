package com.zzq.cloud.platform.service.sys;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzq.cloud.platform.domain.sys.SysUser;
import com.zzq.cloud.platform.model.dto.sys.AddSysUserDto;
import com.zzq.cloud.platform.model.dto.sys.EditUserDto;
import com.zzq.cloud.platform.model.dto.sys.QuerySysUserDto;
import com.zzq.cloud.platform.model.vo.sys.SysUserVo;

/**
 * 用户Service接口
 * @author ZZQ
 * @date 2021-01-25
 */
public interface ISysUserService extends IService<SysUser> {

    IPage<SysUserVo> queryPage(QuerySysUserDto params);

    Integer add(AddSysUserDto user);

    Integer editUser(EditUserDto userDto);

    Integer state(Long userId);

}

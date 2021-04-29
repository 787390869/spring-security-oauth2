package com.zzq.cloud.platform.mapper.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzq.cloud.platform.domain.sys.OAuthUser;
import com.zzq.cloud.platform.domain.sys.SysUser;
import com.zzq.cloud.platform.model.dto.sys.QuerySysUserDto;
import com.zzq.cloud.platform.model.vo.sys.SysUserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 用户Mapper接口
 * @author ZZQ
 * @date 2021-01-25
 */
@Mapper
@Repository
public interface SysUserMapper extends BaseMapper<SysUser> {

    /** PMS 加载用户信息用 */
    OAuthUser findByUsername(@Param("username") String username);

    /** 分页查询用户信息 */
    IPage<SysUserVo> findPageByParams(Page<?> page, @Param("p") QuerySysUserDto params);

    /** 通过用户名查询密码 */
    String findPasswordById(Long userId);

}

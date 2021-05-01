package com.zzq.cloud.platform.mapper.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzq.cloud.platform.domain.sys.SysRole;
import com.zzq.cloud.platform.model.dto.sys.QueryRoleDto;
import com.zzq.cloud.platform.model.vo.sys.SysRoleVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色Mapper接口
 * @author ZZQ
 * @date 2021-01-25
 */
@Mapper
@Repository
public interface SysRoleMapper extends BaseMapper<SysRole> {

    IPage<SysRoleVo> findByParams(Page<?> page, @Param("p")QueryRoleDto params);

    List<String> findAll();
}

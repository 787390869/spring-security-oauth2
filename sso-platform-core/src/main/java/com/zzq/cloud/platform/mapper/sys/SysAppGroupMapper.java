package com.zzq.cloud.platform.mapper.sys;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzq.cloud.platform.domain.sys.SysAppGroup;
import com.zzq.cloud.platform.model.dto.sys.QueryApplicationDto;
import com.zzq.cloud.platform.model.vo.auth.ApplicationVo;
import com.zzq.cloud.platform.model.vo.sys.SysAppGroupVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
/**
 * 应用分组Mapper接口
 * @author ZZQ
 * @date 2021-04-30
 */
@Mapper
@Repository
public interface SysAppGroupMapper extends BaseMapper<SysAppGroup> {

    List<ApplicationVo> findByParams(QueryApplicationDto params);

    List<SysAppGroupVo> findAll();
}
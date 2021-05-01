package com.zzq.cloud.platform.service.sys;

import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzq.cloud.platform.domain.sys.SysAppGroup;
import com.zzq.cloud.platform.model.dto.sys.QueryApplicationDto;
import com.zzq.cloud.platform.model.vo.auth.ApplicationVo;
import com.zzq.cloud.platform.model.vo.sys.SysAppGroupVo;

/**
 * 应用分组Service接口
 * @author ZZQ
 * @date 2021-04-30
 */
public interface ISysAppGroupService extends IService<SysAppGroup> {

    List<ApplicationVo> findByParams(QueryApplicationDto params);

    List<SysAppGroupVo> findAll();

    Integer removeOne(Long groupId);
}
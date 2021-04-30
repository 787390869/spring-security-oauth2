package com.zzq.cloud.platform.service.sys.impl;

import java.util.List;
import java.util.Arrays;

import com.zzq.cloud.platform.domain.sys.SysAppGroup;
import com.zzq.cloud.platform.mapper.sys.SysAppGroupMapper;
import com.zzq.cloud.platform.model.dto.sys.QueryApplicationDto;
import com.zzq.cloud.platform.model.vo.auth.ApplicationVo;
import com.zzq.cloud.platform.model.vo.sys.SysAppGroupVo;
import com.zzq.cloud.platform.service.sys.ISysAppGroupService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.transaction.annotation.Transactional;

/**
 * 应用分组Service业务层处理
 * @author ZZQ
 * @date 2021-04-30
 */
@Service
@RequiredArgsConstructor
public class SysAppGroupServiceImpl extends ServiceImpl<SysAppGroupMapper, SysAppGroup> implements ISysAppGroupService {

    private final SysAppGroupMapper sysAppGroupMapper;

    @Override
    public List<ApplicationVo> findByParams(QueryApplicationDto params) {
        return null;
    }
}
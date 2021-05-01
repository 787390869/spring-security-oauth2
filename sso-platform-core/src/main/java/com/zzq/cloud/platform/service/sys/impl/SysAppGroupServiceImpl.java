package com.zzq.cloud.platform.service.sys.impl;

import java.util.List;
import java.util.Arrays;

import com.zzq.cloud.platform.domain.sys.SysAppGroup;
import com.zzq.cloud.platform.mapper.auth.OAuthClientDetailMapper;
import com.zzq.cloud.platform.mapper.sys.SysAppGroupMapper;
import com.zzq.cloud.platform.model.dto.sys.QueryApplicationDto;
import com.zzq.cloud.platform.model.vo.auth.ApplicationVo;
import com.zzq.cloud.platform.model.vo.sys.SysAppGroupVo;
import com.zzq.cloud.platform.service.sys.ISysAppGroupService;
import com.zzq.cloud.sdk.framework.BusiException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
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
    private final OAuthClientDetailMapper clientDetailMapper;

    @Override
    public List<ApplicationVo> findByParams(QueryApplicationDto params) {
        return sysAppGroupMapper.findByParams(params);
    }

    @Override
    public List<SysAppGroupVo> findAll() {
        return sysAppGroupMapper.findAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer removeOne(Long groupId) {
        SysAppGroup appGroup = sysAppGroupMapper.selectById(groupId);
        if (ObjectUtils.isEmpty(appGroup)) throw new BusiException("不存在此应用组");
        appGroup.setIsDelete(1);
        clientDetailMapper.removeByGroupId(groupId);
        return sysAppGroupMapper.updateById(appGroup);
    }
}
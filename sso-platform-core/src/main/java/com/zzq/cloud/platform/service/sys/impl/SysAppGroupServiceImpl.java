package com.zzq.cloud.platform.service.sys.impl;

import java.util.List;

import com.zzq.cloud.platform.domain.sys.SysAppGroup;
import com.zzq.cloud.platform.mapper.auth.OAuthClientDetailMapper;
import com.zzq.cloud.platform.mapper.sys.SysAppGroupMapper;
import com.zzq.cloud.platform.model.dto.sys.EditGroupDto;
import com.zzq.cloud.platform.model.dto.sys.QueryApplicationDto;
import com.zzq.cloud.platform.model.vo.auth.ApplicationVo;
import com.zzq.cloud.platform.model.vo.sys.SysAppGroupVo;
import com.zzq.cloud.platform.service.sys.ISysAppGroupService;
import com.zzq.cloud.platform.framework.BusiException;
import com.zzq.cloud.platform.util.BeanUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer addGroup(String groupName) {
        QueryWrapper<SysAppGroup> query = new QueryWrapper<>();
        query.eq("group_name", groupName)
             .eq("is_delete", 0);
        if (ObjectUtils.isNotEmpty(sysAppGroupMapper.selectOne(query))) throw new BusiException("已存在该应用组!");

        SysAppGroup appGroup = new SysAppGroup();
        appGroup.setGroupName(groupName);
        return sysAppGroupMapper.insert(appGroup);
    }

    @Override
    public Integer edit(EditGroupDto groupDto) {
        SysAppGroup group = sysAppGroupMapper.selectById(groupDto.getId());
        if (ObjectUtils.isEmpty(group)) throw new BusiException("不存在该应用组");
        BeanUtil.copyNotNullProperties(group, groupDto);
        return sysAppGroupMapper.updateById(group);
    }
}
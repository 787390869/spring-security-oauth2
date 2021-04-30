package com.zzq.cloud.platform.service.auth.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzq.cloud.platform.domain.auth.OAuthClientDetail;
import com.zzq.cloud.platform.domain.sys.SysAppGroup;
import com.zzq.cloud.platform.mapper.auth.OAuthClientDetailMapper;
import com.zzq.cloud.platform.mapper.sys.SysAppGroupMapper;
import com.zzq.cloud.platform.model.dto.sys.QueryApplicationDto;
import com.zzq.cloud.platform.model.vo.auth.ApplicationVo;
import com.zzq.cloud.platform.model.vo.auth.OAuthClientDetailVo;
import com.zzq.cloud.platform.service.auth.IApplicationService;
import com.zzq.cloud.sdk.framework.BusiException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author ZhangZiQiang
 * @CreateTime 2021/4/30
 */
@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl extends ServiceImpl<OAuthClientDetailMapper, OAuthClientDetail> implements IApplicationService {

    private final OAuthClientDetailMapper clientDetailMapper;
    private final SysAppGroupMapper appGroupMapper;

    @Override
    public List<ApplicationVo> queryList(QueryApplicationDto params) {
        List<OAuthClientDetailVo> apps = clientDetailMapper.findByParams(params);
        Map<Long, List<OAuthClientDetailVo>> appMap = apps.stream().collect(Collectors.groupingBy(OAuthClientDetailVo::getGroupId));
        List<ApplicationVo> groupsApps = new ArrayList<>();
        appMap.forEach((key, val) -> {
            ApplicationVo vo = new ApplicationVo();
            SysAppGroup group = appGroupMapper.selectById(key);
            if (ObjectUtils.isNotEmpty(group)) {
                vo.setId(group.getId());
                vo.setGroupName(group.getGroupName());
                vo.setApps(val);
                groupsApps.add(vo);
            }
        });
        return groupsApps;
    }

    @Override
    public IPage<OAuthClientDetailVo> queryPage(QueryApplicationDto params, Boolean isAdmin) {
        if (!isAdmin) params.setUserId(params.getUserId());
        return clientDetailMapper.findByParams(params.page(), params);
    }

    @Override
    public Integer publish(String appId) {
        OAuthClientDetail clientDetail = clientDetailMapper.selectById(appId);
        if (ObjectUtils.isEmpty(clientDetail)) throw new BusiException("应用不存在!");
        clientDetail.setIsPublish(clientDetail.getIsPublish() == 0 ? 1 : 0);
        return clientDetailMapper.updateById(clientDetail);
    }
}

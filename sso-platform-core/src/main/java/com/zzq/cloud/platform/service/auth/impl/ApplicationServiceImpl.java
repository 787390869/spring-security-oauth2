package com.zzq.cloud.platform.service.auth.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzq.cloud.platform.domain.auth.OAuthClientDetail;
import com.zzq.cloud.platform.domain.auth.UserApplication;
import com.zzq.cloud.platform.domain.sys.SysAppGroup;
import com.zzq.cloud.platform.domain.sys.SysUser;
import com.zzq.cloud.platform.mapper.auth.OAuthClientDetailMapper;
import com.zzq.cloud.platform.mapper.auth.UserApplicationMapper;
import com.zzq.cloud.platform.mapper.sys.SysAppGroupMapper;
import com.zzq.cloud.platform.mapper.sys.SysUserMapper;
import com.zzq.cloud.platform.model.dto.sys.AddApplicationDto;
import com.zzq.cloud.platform.model.dto.sys.AllotApplicationDto;
import com.zzq.cloud.platform.model.dto.sys.EditApplicationDto;
import com.zzq.cloud.platform.model.dto.sys.QueryApplicationDto;
import com.zzq.cloud.platform.model.enums.GrantTypeEnum;
import com.zzq.cloud.platform.model.vo.auth.ApplicationVo;
import com.zzq.cloud.platform.model.vo.auth.OAuthClientDetailVo;
import com.zzq.cloud.platform.model.vo.sys.UserApplicationVo;
import com.zzq.cloud.platform.service.auth.IApplicationService;
import com.zzq.cloud.platform.service.sys.IUserApplicationService;
import com.zzq.cloud.platform.framework.BusiException;
import com.zzq.cloud.platform.util.BeanUtil;
import com.zzq.cloud.platform.util.Md5Util;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
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
    private final PasswordEncoder passwordEncoder;
    private final SysUserMapper userMapper;
    private final UserApplicationMapper userApplicationMapper;

    private final IUserApplicationService userApplicationService;

    @Override
    public List<ApplicationVo> queryList(QueryApplicationDto params) {
        List<OAuthClientDetailVo> apps = clientDetailMapper.findByParams(params);
        apps.forEach(app -> {
            app.setIsAuth(app.getAuthorizedGrantTypes().contains("authorization_code"));
        });
        Map<Long, List<OAuthClientDetailVo>> appMap = apps.stream().collect(Collectors.groupingBy(OAuthClientDetailVo::getGroupId));
        List<ApplicationVo> groupsApps = new ArrayList<>();
        appMap.forEach((key, val) -> {
            ApplicationVo vo = new ApplicationVo();
            SysAppGroup group = appGroupMapper.selectById(key);
            if (ObjectUtils.isNotEmpty(group)) {
                vo.setId(group.getId());
                vo.setGroupName(group.getGroupName());
                vo.setApps(val);
                vo.setCreateTime(group.getCreateTime());
                groupsApps.add(vo);
            }
        });
        return groupsApps.stream().sorted(Comparator.comparing(ApplicationVo::getCreateTime)).collect(Collectors.toList());
    }

    @Override
    public List<OAuthClientDetailVo> queryAll(QueryApplicationDto params) {
        return clientDetailMapper.findAll(params);
    }

    @Override
    public IPage<OAuthClientDetailVo> queryPage(QueryApplicationDto params) {
        if (StringUtils.isNotBlank(params.getUsername())) {
            QueryWrapper<SysUser> query = new QueryWrapper<>();
            query.eq("username", params.getUsername());
            SysUser user = userMapper.selectOne(query);
            params.setUserId(ObjectUtils.isNotEmpty(user) ? user.getUserId() : 0);
        }
        IPage<OAuthClientDetailVo> apps = clientDetailMapper.findPage(params.page(), params);
        List<OAuthClientDetailVo> vos = apps.getRecords().stream().peek(app -> {
            app.setIsAuth(app.getAuthorizedGrantTypes().indexOf("authorization_code") > 0);
            app.setGrantTypes(Arrays.asList(app.getAuthorizedGrantTypes().split(",")));
        }).collect(Collectors.toList());
        apps.setRecords(vos);
        return apps;
    }

    @Override
    public Integer publish(String appId) {
        OAuthClientDetail clientDetail = clientDetailMapper.selectById(appId);
        if (ObjectUtils.isEmpty(clientDetail)) throw new BusiException("???????????????!");
        clientDetail.setIsPublish(clientDetail.getIsPublish() == 0 ? 1 : 0);
        return clientDetailMapper.updateById(clientDetail);
    }

    @Override
    public Integer removeOne(String appId) {
        OAuthClientDetail clientDetail = clientDetailMapper.selectById(appId);
        if (ObjectUtils.isEmpty(clientDetail)) throw new BusiException("??????????????????!");
        clientDetail.setIsDelete(1);
        return clientDetailMapper.updateById(clientDetail);
    }

    @Override
    public Integer edit(EditApplicationDto applicationDto) {
        if(applicationDto.getGrantTypes().size() == 0) throw new BusiException("????????????????????????!");

        if (!applicationDto.getGrantTypes().contains(GrantTypeEnum.PASSWORD.getGrantType())
                && !applicationDto.getGrantTypes().contains(GrantTypeEnum.AUTHORIZation_code.getGrantType())) {
            throw new BusiException("??????????????????????????????????????????????????????!");
        }
        OAuthClientDetail client = clientDetailMapper.selectById(applicationDto.getClientId());
        if (ObjectUtils.isEmpty(client)) throw new BusiException("??????????????????");
        BeanUtil.copyNotNullProperties(client, applicationDto);
        client.setAuthorizedGrantTypes(String.join(",", applicationDto.getGrantTypes()));
        client.setClientSecret(passwordEncoder.encode(applicationDto.getOriginalSecret()));
        return clientDetailMapper.updateById(client);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer add(AddApplicationDto applicationDto) {
        OAuthClientDetail client = new OAuthClientDetail();
        BeanUtil.copyAllProperties(client, applicationDto);
        client.setClientId(Md5Util.encodeMd5(UUID.randomUUID().toString()));
        client.setClientSecret(passwordEncoder.encode(applicationDto.getOriginalSecret()));
        client.setIsPublish(0);
        client.setAuthorizedGrantTypes(String.join(",", applicationDto.getGrantTypes()));
        return clientDetailMapper.insert(client);
    }

    @Override
    public Boolean allot(AllotApplicationDto applicationDto) {
        QueryWrapper<UserApplication> query = new QueryWrapper<>();
        query.eq("user_id", applicationDto.getUserId());
        userApplicationService.remove(query);

        List<UserApplication> userApplications = applicationDto.getAppIds().stream().map(appId -> {
            UserApplication userApplication = new UserApplication();
            userApplication.setUserId(applicationDto.getUserId());
            userApplication.setClientId(appId);
            return userApplication;
        }).collect(Collectors.toList());
        return userApplicationService.saveBatch(userApplications);
    }

    @Override
    public List<UserApplicationVo> queryUserApplications(Long userId) {
        return userApplicationMapper.findByUserId(userId);
    }
}

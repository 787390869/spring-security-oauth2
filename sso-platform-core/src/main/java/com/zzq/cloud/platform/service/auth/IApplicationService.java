package com.zzq.cloud.platform.service.auth;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzq.cloud.platform.domain.auth.OAuthClientDetail;
import com.zzq.cloud.platform.model.dto.sys.AddApplicationDto;
import com.zzq.cloud.platform.model.dto.sys.EditApplicationDto;
import com.zzq.cloud.platform.model.dto.sys.QueryApplicationDto;
import com.zzq.cloud.platform.model.vo.auth.ApplicationVo;
import com.zzq.cloud.platform.model.vo.auth.OAuthClientDetailVo;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author ZhangZiQiang
 * @CreateTime 2021/4/30
 */
public interface IApplicationService extends IService<OAuthClientDetail> {

    List<ApplicationVo> queryList(QueryApplicationDto params);

    IPage<OAuthClientDetailVo> queryPage(QueryApplicationDto params);

    Integer publish(String appId);

    Integer removeOne(String appId);

    Integer edit( EditApplicationDto applicationDto);

    Integer add(AddApplicationDto applicationDto);
}

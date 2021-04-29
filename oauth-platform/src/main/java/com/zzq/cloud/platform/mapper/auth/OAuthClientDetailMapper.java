package com.zzq.cloud.platform.mapper.auth;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzq.cloud.platform.domain.auth.OAuthClientDetail;
import com.zzq.cloud.platform.model.vo.auth.OAuthClientDetailVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @Author ZhangZiQiang
 * @CreateTime 2021/4/29
 */
@Mapper
@Repository
public interface OAuthClientDetailMapper extends BaseMapper<OAuthClientDetail> {

    /** 查询当前账号能支配的应用 */
    OAuthClientDetailVo findMyApplication(@Param("userId") Long userId, @Param("clientId") String clientId);

}

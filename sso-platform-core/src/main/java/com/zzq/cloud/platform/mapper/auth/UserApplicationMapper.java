package com.zzq.cloud.platform.mapper.auth;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzq.cloud.platform.domain.auth.UserApplication;
import com.zzq.cloud.platform.model.vo.sys.UserApplicationVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户应用Mapper接口
 * @author ZZQ
 * @date 2021-04-29
 */
@Mapper
@Repository
public interface UserApplicationMapper extends BaseMapper<UserApplication> {


    List<UserApplicationVo> findByUserId(Long userId);

    List<String> findClientNameByUserId(Long userId);

}
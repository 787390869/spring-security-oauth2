package com.zzq.cloud.platform.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.zzq.cloud.sdk.base.BaseController;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import java.util.Date;

/**
 * @author ZZQ
 * @date 2020/1/7 15:41
 */

@Component
public class MetaHandler extends BaseController implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("creator", super.getNickname() ,metaObject);
        this.setFieldValByName("createTime", new Date(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updator", super.getNickname(), metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }
}

package com.demo.code.common.config;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.NumberUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @description: MybatisPlus
 * @author: bing.he
 * @create: 2022-02-23 11:35
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Value("${snowflake.workerId}")
    private long workerId;

    @Value("${snowflake.datacenterId}")
    private long datacenterId;

    @Override
    public void insertFill(MetaObject metaObject) {
        Date date = DateUtil.date();
        this.fillStrategy(metaObject, "createTime", date);
        Object idObj = metaObject.getValue("id");
        if (idObj == null || NumberUtil.parseLong(idObj.toString()) == 0) {
            this.setFieldValByName("id", IdUtil.getSnowflake(workerId, datacenterId).nextId(), metaObject);
        }
        this.fillStrategy(metaObject, "deleted", false);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Date date = DateUtil.date();
        this.fillStrategy(metaObject, "updateTime", date);
        boolean exist = metaObject.hasSetter("id");
        if(exist){
            Object idObj = metaObject.getValue("id");
            if (idObj == null || NumberUtil.parseLong(idObj.toString()) == 0) {
                this.setFieldValByName("id", IdUtil.getSnowflake(workerId, datacenterId).nextId(), metaObject);
            }
        }
    }
}

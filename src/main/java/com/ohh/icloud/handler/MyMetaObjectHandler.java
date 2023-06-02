package com.ohh.icloud.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.ohh.icloud.component.ThreadLocalComponent;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        // 这个的user需要变更
        Long userId = ThreadLocalComponent.getUserId();
        this.setFieldValByName("id", userId, metaObject);
        this.setFieldValByName("createTime", System.currentTimeMillis(), metaObject);
        this.setFieldValByName("createUser", userId, metaObject);
        this.setFieldValByName("updateTime", System.currentTimeMillis(), metaObject);
        this.setFieldValByName("updateUser", userId, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Long userId = ThreadLocalComponent.getUserId();
        this.setFieldValByName("updateTime", System.currentTimeMillis(), metaObject);
        // 这个的user需要变更
        this.setFieldValByName("updateUser", userId, metaObject);
    }
}

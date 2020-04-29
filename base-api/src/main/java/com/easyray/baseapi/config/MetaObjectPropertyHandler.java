package com.easyray.baseapi.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.easyray.baseapi.constant.FieldNameConstant;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Date: 20-4-29
 * @Author: wyy
 */

/**
 * 新增和修改自动填充字段,无须手动赋值
 */
@Component
public class MetaObjectPropertyHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        String createDate = metaObject.findProperty(FieldNameConstant.createDate, true);
        if (createDate != null) {
            this.setFieldValByName(FieldNameConstant.createDate, new Date(), metaObject);
        }
        String modifiedDate = metaObject.findProperty(FieldNameConstant.modifiedDate, true);
        if (modifiedDate != null) {
            this.setFieldValByName(FieldNameConstant.modifiedDate, new Date(), metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        String modifiedDate = metaObject.findProperty(FieldNameConstant.modifiedDate, true);
        if (modifiedDate != null) {
            this.setFieldValByName(FieldNameConstant.modifiedDate, new Date(), metaObject);
        }
    }
}

package com.easyray.auth.config;

import com.easyray.auth.service.impl.SpringSecurityThreadLocal;
import com.easyray.baseapi.autofill.EasyrayMetaObjectHandler;
import com.easyray.baseapi.constant.FieldNameConstant;
import com.easyray.systemapi.entity.User;
import org.apache.ibatis.reflection.MetaObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @Date: 20-4-30
 * @Author: wyy
 */

@Aspect
@Component
public class AutoFillUserPropertyHandler {

    private final Logger logger = LoggerFactory.getLogger(AutoFillUserPropertyHandler.class);

    @Autowired
    private SpringSecurityThreadLocal springSecurityThreadLocal;

    @Pointcut("execution(void com.easyray.baseapi.autofill.EasyrayMetaObjectHandler.insertFill(..))")
    public void fillUser() {
    }

    @Before("fillUser()")
    public void insertFill(JoinPoint joinPoint) {
        Object target = joinPoint.getTarget();
        Object[] args = joinPoint.getArgs();
        User user = springSecurityThreadLocal.getUser();
        if (target instanceof EasyrayMetaObjectHandler
                && args.length > 0 && args[0] instanceof MetaObject
                && user != null) {
            EasyrayMetaObjectHandler easyrayMetaObjectHandler = (EasyrayMetaObjectHandler) target;
            MetaObject metaObject = (MetaObject) args[0];
            String userId = metaObject.findProperty(FieldNameConstant.userId, true);
            if (userId != null) {
                logger.debug("{} auto set {} value: {}", metaObject.getOriginalObject().getClass().getSimpleName(), FieldNameConstant.userId, user.getId());
                easyrayMetaObjectHandler.setFieldValByName(FieldNameConstant.userId, user.getId(), metaObject);
            }

            String fullName = metaObject.findProperty(FieldNameConstant.fullName, true);
            if (fullName != null) {
                logger.debug("{} auto set {} value: {}", metaObject.getOriginalObject().getClass().getSimpleName(), FieldNameConstant.fullName, user.getFullName());
                easyrayMetaObjectHandler.setFieldValByName(FieldNameConstant.fullName, user.getFullName(), metaObject);
            }
        }
    }

    @Pointcut("execution(void com.easyray.baseapi.autofill.EasyrayMetaObjectHandler.updateFill(..))")
    public void updateUser() {
    }

    @Before("updateUser()")
    public void updateFill(JoinPoint joinPoint) {
        Object target = joinPoint.getTarget();
        Object[] args = joinPoint.getArgs();
        User user = springSecurityThreadLocal.getUser();
        if (target instanceof EasyrayMetaObjectHandler
                && args.length > 0 && args[0] instanceof MetaObject
                && user != null) {
            EasyrayMetaObjectHandler easyrayMetaObjectHandler = (EasyrayMetaObjectHandler) target;
            MetaObject metaObject = (MetaObject) args[0];
            String modifiedUserId = metaObject.findProperty(FieldNameConstant.modifiedUserId, true);
            if (modifiedUserId != null) {
                logger.debug("{} auto set {} value: {}", metaObject.getOriginalObject().getClass().getSimpleName(), FieldNameConstant.modifiedUserId, user.getId());
                easyrayMetaObjectHandler.setFieldValByName(FieldNameConstant.modifiedUserId, user.getId(), metaObject);
            }

            String modifiedUserFullName = metaObject.findProperty(FieldNameConstant.modifiedUserFullName, true);
            if (modifiedUserFullName != null) {
                logger.debug("{} auto set {} value: {}", metaObject.getOriginalObject().getClass().getSimpleName(), FieldNameConstant.modifiedUserFullName, user.getFullName());
                easyrayMetaObjectHandler.setFieldValByName(FieldNameConstant.modifiedUserFullName, user.getFullName(), metaObject);
            }
        }
    }


}

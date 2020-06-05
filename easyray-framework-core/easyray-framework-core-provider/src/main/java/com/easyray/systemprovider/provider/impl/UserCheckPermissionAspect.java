package com.easyray.systemprovider.provider.impl;

import com.easyray.baseapi.provider.CheckPermissionAspect;
import com.easyray.coreapi.service.UserCheckPermission;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Date: 2020-02_22
 * @Author: wyy
 */
@Aspect
@Component
public class UserCheckPermissionAspect extends CheckPermissionAspect<UserCheckPermission> {

    @Autowired
    private UserCheckPermission checkPermission;

    @Override
    public UserCheckPermission getCheckPermission() {
        return checkPermission;
    }

    @Pointcut("target(UserProviderImpl)")
    @Override
    public void pointcut() {

    }
}

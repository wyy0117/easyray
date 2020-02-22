package com.easyray.systemprovider.provider.impl;

import com.easyray.baseapi.provider.CheckPermissionAspect;
import com.easyray.systemapi.service.UserRoleCheckPermission;
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
public class UserRoleCheckPermissionAspect extends CheckPermissionAspect<UserRoleCheckPermission> {

    @Autowired
    private UserRoleCheckPermission checkPermission;

    @Override
    public UserRoleCheckPermission getCheckPermission() {
        return checkPermission;
    }

    @Pointcut("target(UserRoleProviderImpl)")
    @Override
    public void pointcut() {

    }
}

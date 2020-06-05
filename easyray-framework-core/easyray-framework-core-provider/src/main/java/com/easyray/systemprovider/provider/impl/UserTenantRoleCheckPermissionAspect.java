package com.easyray.systemprovider.provider.impl;

import com.easyray.baseapi.provider.CheckPermissionAspect;
import com.easyray.coreapi.service.UserTenantRoleCheckPermission;
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
public class UserTenantRoleCheckPermissionAspect extends CheckPermissionAspect<UserTenantRoleCheckPermission> {

    @Autowired
    private UserTenantRoleCheckPermission checkPermission;

    @Override
    public UserTenantRoleCheckPermission getCheckPermission() {
        return checkPermission;
    }

    @Pointcut("target(UserTenantRoleProviderImpl)")
    @Override
    public void pointcut() {

    }
}

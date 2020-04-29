package com.easyray.systemprovider.provider.impl;

import com.easyray.baseapi.provider.CheckPermissionAspect;
import com.easyray.systemapi.service.UserGroupRoleCheckPermission;
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
public class UserGroupRoleCheckPermissionAspect extends CheckPermissionAspect<UserGroupRoleCheckPermission> {

    @Autowired
    private UserGroupRoleCheckPermission checkPermission;

    @Override
    public UserGroupRoleCheckPermission getCheckPermission() {
        return checkPermission;
    }

    @Pointcut("target(UserGroupRoleProviderImpl)")
    @Override
    public void pointcut() {

    }
}

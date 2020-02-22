package com.easyray.systemprovider.provider.impl;

import com.easyray.baseapi.provider.CheckPermissionAspect;
import com.easyray.systemapi.service.RoleCheckPermission;
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
public class RoleCheckPermissionAspect extends CheckPermissionAspect<RoleCheckPermission> {

    @Autowired
    private RoleCheckPermission checkPermission;

    @Override
    public RoleCheckPermission getCheckPermission() {
        return checkPermission;
    }

    @Pointcut("target(RoleProviderImpl)")
    @Override
    public void pointcut() {

    }
}

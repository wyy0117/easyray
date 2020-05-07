package com.easyray.systemprovider.provider.impl;

import com.easyray.baseapi.provider.CheckPermissionAspect;
import com.easyray.systemapi.service.TenantCheckPermission;
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
public class TenantCheckPermissionAspect extends CheckPermissionAspect<TenantCheckPermission> {

    @Autowired
    private TenantCheckPermission checkPermission;

    @Override
    public TenantCheckPermission getCheckPermission() {
        return checkPermission;
    }

    @Pointcut("target(TenantProviderImpl)")
    @Override
    public void pointcut() {

    }
}

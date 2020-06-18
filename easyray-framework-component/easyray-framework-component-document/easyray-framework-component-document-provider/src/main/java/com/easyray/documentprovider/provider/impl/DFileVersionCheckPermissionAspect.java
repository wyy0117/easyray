package com.easyray.documentprovider.provider.impl;

import com.easyray.baseapi.provider.CheckPermissionAspect;
import com.easyray.documentapi.provider.DFileVersionCheckPermission;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Date: 2020-06_18
 * @Author: wyy
 */
@Aspect
@Component
public class DFileVersionCheckPermissionAspect extends CheckPermissionAspect<DFileVersionCheckPermission> {

    @Autowired
    private DFileVersionCheckPermission checkPermission;

    @Override
    public DFileVersionCheckPermission getCheckPermission() {
        return checkPermission;
    }

    @Pointcut("target(DFileVersionProviderImpl)")
    @Override
    public void pointcut() {

    }
}

package com.easyray.documentprovider.provider.impl;

import com.easyray.baseapi.provider.CheckPermissionAspect;
import com.easyray.documentapi.provider.DFileCheckPermission;
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
public class DFileCheckPermissionAspect extends CheckPermissionAspect<DFileCheckPermission> {

    @Autowired
    private DFileCheckPermission checkPermission;

    @Override
    public DFileCheckPermission getCheckPermission() {
        return checkPermission;
    }

    @Pointcut("target(DFileProviderImpl)")
    @Override
    public void pointcut() {

    }
}

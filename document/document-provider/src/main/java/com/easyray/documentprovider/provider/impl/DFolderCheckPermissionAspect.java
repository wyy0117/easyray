package com.easyray.documentprovider.provider.impl;

import com.easyray.baseapi.provider.CheckPermissionAspect;
import com.easyray.documentapi.provider.DFolderCheckPermission;
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
public class DFolderCheckPermissionAspect extends CheckPermissionAspect<DFolderCheckPermission> {

    @Autowired
    private DFolderCheckPermission checkPermission;

    @Override
    public DFolderCheckPermission getCheckPermission() {
        return checkPermission;
    }

    @Pointcut("target(DFolderProviderImpl)")
    @Override
    public void pointcut() {

    }
}

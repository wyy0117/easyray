package com.easyray.systemprovider.provider.impl;

import com.easyray.baseapi.provider.CheckPermissionAspect;
import com.easyray.systemapi.service.GroupCheckPermission;
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
public class GroupCheckPermissionAspect extends CheckPermissionAspect<GroupCheckPermission> {

    @Autowired
    private GroupCheckPermission checkPermission;

    @Override
    public GroupCheckPermission getCheckPermission() {
        return checkPermission;
    }

    @Pointcut("target(GroupProviderImpl)")
    @Override
    public void pointcut() {

    }
}

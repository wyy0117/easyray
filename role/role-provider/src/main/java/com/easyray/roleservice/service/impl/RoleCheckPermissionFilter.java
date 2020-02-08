package com.easyray.roleservice.service.impl;

import com.alibaba.dubbo.config.spring.ServiceBean;
import com.easyray.roleapi.service.RoleCheckPermission;
import com.easyray.baseapi.service.BaseCheckPermissionFilter;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @Date: 2020-02_08
 * @Author: wyy
 */
@Component
public class RoleCheckPermissionFilter extends BaseCheckPermissionFilter<RoleCheckPermission> {

    @Override
    public RoleCheckPermission getCheckPermission() {
        ApplicationContext applicationContext = ServiceBean.getSpringContext();
        return applicationContext.getBean(RoleCheckPermission.class);
    }
}

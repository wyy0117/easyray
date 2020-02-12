package com.easyray.systemprovider.provider.impl;

import com.alibaba.dubbo.config.spring.ServiceBean;
import com.easyray.baseapi.provider.BaseCheckPermissionFilter;
import com.easyray.systemapi.service.UserRoleCheckPermission;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @Date: 2020-02_12
 * @Author: wyy
 */
@Component
public class UserRoleCheckPermissionFilter extends BaseCheckPermissionFilter<UserRoleCheckPermission> {

    @Override
    public UserRoleCheckPermission getCheckPermission() {
        ApplicationContext applicationContext = ServiceBean.getSpringContext();
        return applicationContext.getBean(UserRoleCheckPermission.class);
    }
}

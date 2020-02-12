package com.easyray.systemprovider.provider.impl;

import com.alibaba.dubbo.config.spring.ServiceBean;
import com.easyray.baseapi.provider.BaseCheckPermissionFilter;
import com.easyray.systemapi.service.UserCheckPermission;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @Date: 20-1-29
 * @Author: wyy
 */
@Component
public class UserCheckPermissionFilter extends BaseCheckPermissionFilter<UserCheckPermission> {

    @Override
    public UserCheckPermission getCheckPermission() {
        ApplicationContext applicationContext = ServiceBean.getSpringContext();
        return applicationContext.getBean(UserCheckPermission.class);
    }
}

package com.wyy.userservice.service.impl;

import com.alibaba.dubbo.config.spring.ServiceBean;
import com.wyy.baseapi.service.BaseCheckPermissionFilter;
import com.wyy.easyry.service.UserCheckPermission;
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

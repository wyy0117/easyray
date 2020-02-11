package com.easyray.teamprovider.service.impl;

import com.alibaba.dubbo.config.spring.ServiceBean;
import com.easyray.baseapi.provider.BaseCheckPermissionFilter;
import com.easyray.teamapi.service.TeamCheckPermission;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @Date: 2020-02_11
 * @Author: wyy
 */
@Component
public class TeamCheckPermissionFilter extends BaseCheckPermissionFilter<TeamCheckPermission> {

    @Override
    public TeamCheckPermission getCheckPermission() {
        ApplicationContext applicationContext = ServiceBean.getSpringContext();
        return applicationContext.getBean(TeamCheckPermission.class);
    }
}

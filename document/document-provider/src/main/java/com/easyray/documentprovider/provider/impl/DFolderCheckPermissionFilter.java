package com.easyray.documentprovider.provider.impl;

import com.alibaba.dubbo.config.spring.ServiceBean;
import com.easyray.baseapi.provider.BaseCheckPermissionFilter;
import com.easyray.documentapi.provider.DFolderCheckPermission;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @Date: 2020-02_13
 * @Author: wyy
 */
@Component
public class DFolderCheckPermissionFilter extends BaseCheckPermissionFilter<DFolderCheckPermission> {

    @Override
    public DFolderCheckPermission getCheckPermission() {
        ApplicationContext applicationContext = ServiceBean.getSpringContext();
        return applicationContext.getBean(DFolderCheckPermission.class);
    }
}

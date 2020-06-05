package com.easyray.systemprovider.provider.impl;

import com.easyray.baseapi.provider.EasyrayServiceImpl;
import com.easyray.coreapi.entity.Tenant;
import com.easyray.coreapi.service.TenantCheckPermission;
import com.easyray.systemprovider.mapper.TenantMapper;
import org.springframework.stereotype.Component;

/**
 * @Date: 2020-02_12
 * @Author: wyy
 */
@Component
public class TenantCheckPermissionImpl extends EasyrayServiceImpl<TenantMapper, Tenant> implements TenantCheckPermission {

    @Override
    public Tenant fetchByName(String name) {
        return null;
    }
}

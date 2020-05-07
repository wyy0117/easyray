package com.easyray.systemprovider.provider.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.easyray.baseapi.provider.EasyrayServiceImpl;
import com.easyray.systemapi.entity.UserTenantRole;
import com.easyray.systemapi.service.UserTenantRoleLocalProvider;
import com.easyray.systemprovider.mapper.UserTenantRoleMapper;
import org.springframework.stereotype.Component;

/**
 * @author wyy
 * @since 2020-02_12
 */
@Service
@Component
public class UserTenantRoleLocalProviderImpl extends EasyrayServiceImpl<UserTenantRoleMapper, UserTenantRole> implements UserTenantRoleLocalProvider {


}

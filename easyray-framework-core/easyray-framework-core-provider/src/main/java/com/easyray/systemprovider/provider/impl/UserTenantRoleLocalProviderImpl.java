package com.easyray.systemprovider.provider.impl;

import com.easyray.baseapi.provider.EasyrayServiceImpl;
import com.easyray.coreapi.entity.UserTenantRole;
import com.easyray.coreapi.service.UserTenantRoleLocalProvider;
import com.easyray.systemprovider.mapper.UserTenantRoleMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

/**
 * @author wyy
 * @since 2020-02_12
 */
@DubboService
@Service
public class UserTenantRoleLocalProviderImpl extends EasyrayServiceImpl<UserTenantRoleMapper, UserTenantRole> implements UserTenantRoleLocalProvider {


}

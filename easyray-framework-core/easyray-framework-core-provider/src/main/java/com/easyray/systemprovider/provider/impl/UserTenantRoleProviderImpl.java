package com.easyray.systemprovider.provider.impl;

import com.easyray.coreapi.service.UserTenantRoleProvider;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

/**
 * @Date: 2020-02_12
 * @Author: wyy
 */
@DubboService
@Service
public class UserTenantRoleProviderImpl extends UserTenantRoleLocalProviderImpl implements UserTenantRoleProvider {

}

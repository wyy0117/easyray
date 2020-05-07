package com.easyray.systemprovider.provider.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.easyray.systemapi.service.UserTenantRoleProvider;
import org.springframework.stereotype.Component;

/**
 * @Date: 2020-02_12
 * @Author: wyy
 */
@Service
@Component
public class UserTenantRoleProviderImpl extends UserTenantRoleLocalProviderImpl implements UserTenantRoleProvider {

}

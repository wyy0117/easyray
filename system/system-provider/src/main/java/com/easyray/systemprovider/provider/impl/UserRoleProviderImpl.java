package com.easyray.systemprovider.provider.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.easyray.systemapi.service.UserRoleProvider;
import org.springframework.stereotype.Component;

/**
 * @Date: 2020-02_12
 * @Author: wyy
 */
@Service(filter = {"userRoleCheckPermissionFilter"})
@Component
public class UserRoleProviderImpl extends UserRoleLocalProviderImpl implements UserRoleProvider {

}

package com.easyray.systemprovider.provider.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.easyray.systemapi.service.UserGroupRoleProvider;
import org.springframework.stereotype.Component;

/**
 * @Date: 2020-02_12
 * @Author: wyy
 */
@Service(filter = {"userGroupRoleCheckPermissionFilter"})
@Component
public class UserGroupRoleProviderImpl extends UserGroupRoleLocalProviderImpl implements UserGroupRoleProvider {

}

package com.easyray.systemprovider.provider.impl;

import com.easyray.baseapi.provider.EasyrayServiceImpl;
import com.easyray.systemapi.entity.UserTenantRole;
import com.easyray.systemapi.service.UserTenantRoleCheckPermission;
import com.easyray.systemprovider.mapper.UserTenantRoleMapper;
import org.springframework.stereotype.Component;

/**
 * @Date: 2020-02_12
 * @Author: wyy
 */
@Component
public class UserTenantRoleCheckPermissionImpl extends EasyrayServiceImpl<UserTenantRoleMapper, UserTenantRole> implements UserTenantRoleCheckPermission {

}

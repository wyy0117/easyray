package com.easyray.systemprovider.provider.impl;

import com.easyray.baseapi.provider.EasyrayServiceImpl;
import com.easyray.coreapi.entity.Role;
import com.easyray.coreapi.entity.User;
import com.easyray.coreapi.entity.UserRole;
import com.easyray.coreapi.service.UserRoleCheckPermission;
import com.easyray.systemprovider.mapper.UserRoleMapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Date: 2020-02_12
 * @Author: wyy
 */
@Component
public class UserRoleCheckPermissionImpl extends EasyrayServiceImpl<UserRoleMapper, UserRole> implements UserRoleCheckPermission {

    @Override
    public List<User> findUserByRoleId(long roleId) {
        return null;
    }

    @Override
    public List<Role> findRoleByUserId(long userId) {
        return null;
    }
}

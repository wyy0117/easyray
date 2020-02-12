package com.easyray.systemapi.service;

import com.easyray.baseapi.provider.BaseLocalProvider;
import com.easyray.systemapi.entity.Role;
import com.easyray.systemapi.entity.User;
import com.easyray.systemapi.entity.UserRole;

import java.util.List;

/**
 * @Date: 2020-02_12
 * @Author: wyy
 */
public interface UserRoleLocalProvider extends BaseLocalProvider<UserRole> {

    public List<User> findUserByRoleId(long roleId);

    public List<Role> findRoleByUserId(long userId);

}

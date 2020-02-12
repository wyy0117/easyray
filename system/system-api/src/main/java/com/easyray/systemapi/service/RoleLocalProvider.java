package com.easyray.systemapi.service;

import com.easyray.baseapi.provider.BaseLocalProvider;
import com.easyray.common.exception.EntityNotExistException;
import com.easyray.systemapi.entity.Role;

/**
 * @Date: 2020-02_08
 * @Author: wyy
 */
public interface RoleLocalProvider extends BaseLocalProvider<Role> {

    public Role fetchByName(String name);

    public Role findByName(String name) throws EntityNotExistException;

}

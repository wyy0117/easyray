package com.easyray.roleapi.service;

import com.easyray.roleapi.entity.Role;
import com.easyray.baseapi.service.BaseLocalService;

/**
 * @Date: 2020-02_08
 * @Author: wyy
 */
public interface RoleLocalService extends BaseLocalService<Role> {

    public Role fetchByName(String name);

}

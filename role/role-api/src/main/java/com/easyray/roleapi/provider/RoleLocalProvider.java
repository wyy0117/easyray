package com.easyray.roleapi.provider;

import com.easyray.baseapi.provider.BaseLocalProvider;
import com.easyray.roleapi.entity.Role;

/**
 * @Date: 2020-02_08
 * @Author: wyy
 */
public interface RoleLocalProvider extends BaseLocalProvider<Role> {

    public Role fetchByName(String name);

}

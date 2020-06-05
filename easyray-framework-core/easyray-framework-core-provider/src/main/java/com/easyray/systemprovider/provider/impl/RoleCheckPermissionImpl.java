package com.easyray.systemprovider.provider.impl;

import com.easyray.baseapi.provider.EasyrayServiceImpl;
import com.easyray.common.exception.EntityNotExistException;
import com.easyray.coreapi.entity.Role;
import com.easyray.coreapi.service.RoleCheckPermission;
import com.easyray.systemprovider.mapper.RoleMapper;
import org.springframework.stereotype.Component;

/**
 * @Date: 2020-02_08
 * @Author: wyy
 */
@Component
public class RoleCheckPermissionImpl extends EasyrayServiceImpl<RoleMapper, Role> implements RoleCheckPermission {

    @Override
    public Role fetchByName(String name) {
        return null;
    }

    @Override
    public Role findByName(String name) throws EntityNotExistException {
        return null;
    }

}

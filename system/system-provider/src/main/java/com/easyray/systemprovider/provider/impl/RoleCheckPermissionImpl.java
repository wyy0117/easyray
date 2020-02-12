package com.easyray.systemprovider.provider.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.easyray.common.exception.EntityNotExistException;
import com.easyray.systemapi.entity.Role;
import com.easyray.systemapi.service.RoleCheckPermission;
import com.easyray.systemprovider.mapper.RoleMapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Date: 2020-02_08
 * @Author: wyy
 */
@Component
public class RoleCheckPermissionImpl extends ServiceImpl<RoleMapper, Role> implements RoleCheckPermission {

    @Override
    public Role fetchByName(String name) {
        return null;
    }

    @Override
    public Role findByName(String name) throws EntityNotExistException {
        return null;
    }

}

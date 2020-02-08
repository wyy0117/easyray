package com.easyray.roleservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.easyray.roleapi.entity.Role;
import com.easyray.roleapi.service.RoleCheckPermission;
import com.easyray.roleservice.mapper.RoleMapper;
import org.springframework.stereotype.Component;

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
}

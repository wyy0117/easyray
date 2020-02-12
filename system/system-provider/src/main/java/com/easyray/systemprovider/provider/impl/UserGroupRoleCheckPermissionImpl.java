package com.easyray.systemprovider.provider.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.easyray.systemapi.entity.UserGroupRole;
import com.easyray.systemapi.service.UserGroupRoleCheckPermission;
import com.easyray.systemprovider.mapper.UserGroupRoleMapper;
import org.springframework.stereotype.Component;

/**
 * @Date: 2020-02_12
 * @Author: wyy
 */
@Component
public class UserGroupRoleCheckPermissionImpl extends ServiceImpl<UserGroupRoleMapper, UserGroupRole> implements UserGroupRoleCheckPermission {

}

package com.easyray.systemprovider.provider.impl;

import com.easyray.baseapi.provider.EasyrayServiceImpl;
import com.easyray.coreapi.entity.Role;
import com.easyray.coreapi.entity.User;
import com.easyray.coreapi.entity.UserRole;
import com.easyray.coreapi.service.UserRoleLocalProvider;
import com.easyray.systemprovider.mapper.UserRoleMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wyy
 * @since 2020-02_12
 */
@DubboService
@Service
public class UserRoleLocalProviderImpl extends EasyrayServiceImpl<UserRoleMapper, UserRole> implements UserRoleLocalProvider {


    @Override
    public List<User> findUserByRoleId(long roleId) {
        return getBaseMapper().findUserByRoleId(roleId);
    }

    @Override
    public List<Role> findRoleByUserId(long userId) {
        return getBaseMapper().findRoleByUserId(userId);
    }
}

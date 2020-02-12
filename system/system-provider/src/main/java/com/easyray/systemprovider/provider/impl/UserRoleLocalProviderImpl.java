package com.easyray.systemprovider.provider.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.easyray.systemapi.entity.Role;
import com.easyray.systemapi.entity.User;
import com.easyray.systemapi.entity.UserRole;
import com.easyray.systemapi.service.UserRoleLocalProvider;
import com.easyray.systemprovider.mapper.UserRoleMapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author wyy
 * @since 2020-02_12
 */
@Service
@Component
public class UserRoleLocalProviderImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleLocalProvider {


    @Override
    public List<User> findUserByRoleId(long roleId) {
        return getBaseMapper().findUserByRoleId(roleId);
    }

    @Override
    public List<Role> findRoleByUserId(long userId) {
        return getBaseMapper().findRoleByUserId(userId);
    }
}

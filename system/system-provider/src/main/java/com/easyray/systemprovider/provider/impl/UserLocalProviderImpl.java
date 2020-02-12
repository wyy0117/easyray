package com.easyray.systemprovider.provider.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.easyray.common.exception.EntityNotExistException;
import com.easyray.common.exception.filter.CustomThrowable;
import com.easyray.idgeneratorapi.service.IdService;
import com.easyray.systemapi.constant.RoleNameConstant;
import com.easyray.systemapi.entity.Role;
import com.easyray.systemapi.entity.User;
import com.easyray.systemapi.entity.UserRole;
import com.easyray.systemapi.service.RoleLocalProvider;
import com.easyray.systemapi.service.UserLocalProvider;
import com.easyray.systemapi.service.UserRoleLocalProvider;
import com.easyray.systemprovider.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author easyray
 * @since 2020-01-26
 */
@Service
@Component
@Transactional
public class UserLocalProviderImpl extends ServiceImpl<UserMapper, User> implements UserLocalProvider {

    @Autowired
    @Qualifier("roleLocalProviderImpl")
    private RoleLocalProvider roleLocalProvider;

    @Autowired
    @Qualifier("userRoleLocalProviderImpl")
    private UserRoleLocalProvider userRoleLocalProvider;

    @Reference
    private IdService idService;

    /**
     * 创建用户的时候，肯定就有一个{@link RoleNameConstant#USER_ROLE_NAME}角色
     *
     * @param entity
     * @return
     */
    @Override
    public boolean save(User entity) {
        Role role = roleLocalProvider.fetchByName(RoleNameConstant.USER_ROLE_NAME);
        UserRole userRole = new UserRole(idService.nextId(UserRole.class.getName()), entity.getId(), role.getId());
        userRoleLocalProvider.save(userRole);
        return super.save(entity);
    }

    @Override
    public User findByUsername(String username) throws EntityNotExistException {
        User user = fetchByUsername(username);
        if (user == null) {
            throw new EntityNotExistException(new CustomThrowable(User.class, "username: " + username));
        }
        return user;
    }

    @Override
    public User fetchByUsername(String username) {

        return getOne(new QueryWrapper<User>().eq("username", username), false);
    }

}

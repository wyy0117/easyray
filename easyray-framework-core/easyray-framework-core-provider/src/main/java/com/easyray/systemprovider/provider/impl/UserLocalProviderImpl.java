package com.easyray.systemprovider.provider.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.easyray.baseapi.constant.RoleNameConstant;
import com.easyray.baseapi.provider.EasyrayServiceImpl;
import com.easyray.common.exception.EntityNotExistException;
import com.easyray.coreapi.entity.Role;
import com.easyray.coreapi.entity.User;
import com.easyray.coreapi.entity.UserRole;
import com.easyray.coreapi.service.RoleLocalProvider;
import com.easyray.coreapi.service.UserLocalProvider;
import com.easyray.coreapi.service.UserRoleLocalProvider;
import com.easyray.idgeneratorapi.provider.IdService;
import com.easyray.systemprovider.mapper.UserMapper;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wyy
 * @since 2020-01-26
 */
@DubboService
@Service
@Transactional
public class UserLocalProviderImpl extends EasyrayServiceImpl<UserMapper, User> implements UserLocalProvider {

    @Autowired
    @Qualifier("roleLocalProviderImpl")
    private RoleLocalProvider roleLocalProvider;

    @Autowired
    @Qualifier("userRoleLocalProviderImpl")
    private UserRoleLocalProvider userRoleLocalProvider;

    @DubboReference
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

        return findOneByQuery(new QueryWrapper<User>().lambda().eq(User::getUsername, username));
    }

    @Override
    public User fetchByUsername(String username) {

        return fetchOneByQuery(new QueryWrapper<User>().lambda().eq(User::getUsername, username));
    }

    @Override
    public User fetchByPhone(String phone) {
        return fetchOneByQuery(new QueryWrapper<User>().lambda().eq(User::getPhone, phone));
    }

}

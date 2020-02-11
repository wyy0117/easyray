package com.easyray.userservice.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.easyray.common.exception.EasyCustomException;
import com.easyray.common.exception.EntityNotExistException;
import com.easyray.common.exception.NoPermissionException;
import com.easyray.common.exception.filter.CustomThrowable;
import com.easyray.userapi.entity.User;
import com.easyray.userapi.service.UserLocalProvider;
import com.easyray.userservice.mapper.UserMapper;
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

    @Override
    public void testException() throws NoPermissionException, EasyCustomException, EntityNotExistException {
        if (true) {
//            throw new EasyCustomException(new CustomThrowable(UserErrorCode.USERNAME_EXIST, "用户名已存在"));
            throw new EntityNotExistException(new CustomThrowable(User.class, "123"));
//            throw new NoPermissionException();
        }
    }

}

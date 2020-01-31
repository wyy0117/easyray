package com.wyy.userservice.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wyy.baseapi.customexception.CustomThrowable;
import com.wyy.baseapi.exception.EasyCustomException;
import com.wyy.baseapi.exception.EntityNotExistException;
import com.wyy.baseapi.exception.NoPermissionException;
import com.wyy.easyry.entity.User;
import com.wyy.easyry.service.UserLocalService;
import com.wyy.userservice.mapper.UserMapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wyy
 * @since 2020-01-26
 */
@Service
@Component("userLocalService")
public class UserLocalServiceImpl extends ServiceImpl<UserMapper, User> implements UserLocalService {

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

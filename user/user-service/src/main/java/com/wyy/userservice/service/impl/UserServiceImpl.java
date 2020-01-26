package com.wyy.userservice.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.wyy.easyry.entity.User;
import com.wyy.easyry.service.UserService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wyy
 * @since 2020-01-26
 */
@Component("userService")
@Service
public class UserServiceImpl implements UserService {

    @Override
    public User findByUsername(String username) {
        return null;
    }

    @Override
    public User add(User entity) {
        return null;
    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public User update(User entity) {
        return null;
    }

    @Override
    public User find(Long primeKey) {
        return null;
    }

    @Override
    public User fetch(Long primeKey) {
        return null;
    }

    @Override
    public List<User> findAll(List<Long> primeKeyList) {
        return null;
    }
}

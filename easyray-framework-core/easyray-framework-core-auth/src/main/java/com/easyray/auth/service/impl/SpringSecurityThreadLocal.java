package com.easyray.auth.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.easyray.common.exception.EntityNotExistException;
import com.easyray.coreapi.entity.User;
import com.easyray.coreapi.service.UserLocalProvider;
import org.springframework.stereotype.Component;

/**
 * @Date: 20-2-10
 * @Author: wyy
 */
@Component
public class SpringSecurityThreadLocal {

    @Reference(check = false)
    private UserLocalProvider userLocalProvider;
    private ThreadLocal<User> threadLocal = new ThreadLocal<>();

    public void setUser(User user) {
        threadLocal.set(user);
    }

    public void setUser(String username) throws EntityNotExistException {
        setUser(userLocalProvider.findByUsername(username));
    }

    public User getUser() {
        return threadLocal.get();
    }
}

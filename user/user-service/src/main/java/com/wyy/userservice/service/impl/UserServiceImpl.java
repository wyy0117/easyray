package com.wyy.userservice.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.wyy.easyry.entity.User;
import com.wyy.easyry.service.UserService;
import org.springframework.stereotype.Component;

/**
 * @Date: 20-1-27
 * @Author: wyy
 */
@Service(filter = {"userCheckPermissionFilter"})
@Component
public class UserServiceImpl extends UserLocalServiceImpl implements UserService {

    @Override
    public User findByUsername(String username) {
        return super.findByUsername(username);
    }
}

package com.easyray.userservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.easyray.common.exception.NoPermissionException;
import com.easyray.userapi.entity.User;
import com.easyray.userapi.service.UserCheckPermission;
import com.easyray.userservice.mapper.UserMapper;
import org.springframework.stereotype.Component;

/**
 * @Date: 20-1-28
 * @Author: wyy
 */
@Component
public class UserCheckPermissionImpl extends ServiceImpl<UserMapper, User> implements UserCheckPermission {
    @Override
    public User findByUsername(String username) {
        return null;
    }

    @Override
    public User fetchByUsername(String username) {
        return null;
    }

    @Override
    public void testException() throws NoPermissionException {
        
    }
}

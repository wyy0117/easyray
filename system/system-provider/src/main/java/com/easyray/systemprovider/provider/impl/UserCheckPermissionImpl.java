package com.easyray.systemprovider.provider.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.easyray.common.exception.NoPermissionException;
import com.easyray.systemapi.entity.User;
import com.easyray.systemapi.service.UserCheckPermission;
import com.easyray.systemprovider.mapper.UserMapper;
import org.springframework.stereotype.Component;

import java.util.List;

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


}

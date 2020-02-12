package com.easyray.systemapi.service;

import com.easyray.baseapi.provider.BaseLocalProvider;
import com.easyray.common.exception.EasyCustomException;
import com.easyray.common.exception.EntityNotExistException;
import com.easyray.common.exception.NoPermissionException;
import com.easyray.systemapi.entity.User;

import java.util.List;

/**
 * @Date: 20-1-26
 * @Author: wyy
 */
public interface UserLocalProvider extends BaseLocalProvider<User> {

    public User findByUsername(String username) throws EntityNotExistException;

    public User fetchByUsername(String username);

}

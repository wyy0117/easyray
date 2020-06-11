package com.easyray.coreapi.service;

import com.easyray.baseapi.provider.BaseLocalProvider;
import com.easyray.common.exception.EntityNotExistException;
import com.easyray.coreapi.entity.User;

/**
 * @Date: 20-1-26
 * @Author: wyy
 */
public interface UserLocalProvider extends BaseLocalProvider<User> {

    public User findByUsername(String username) throws EntityNotExistException;

    public User fetchByUsername(String username);

    /**
     * 使用手机号登录时用得到，不能检查权限
     *
     * @param phone
     * @return
     */
    public User fetchByPhone(String phone);

}

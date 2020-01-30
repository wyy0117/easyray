package com.wyy.easyry.service;

import com.wyy.baseapi.service.BaseLocalService;
import com.wyy.easyry.entity.User;

/**
 * @Date: 20-1-26
 * @Author: wyy
 */
public interface UserLocalService extends BaseLocalService<User> {

    public User findByUsername(String username);

    public User fetchByUsername(String username);

}

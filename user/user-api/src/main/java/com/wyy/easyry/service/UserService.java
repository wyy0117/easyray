package com.wyy.easyry.service;

import com.wyy.baseapi.service.BaseService;
import com.wyy.easyry.entity.User;

/**
 * @Date: 20-1-26
 * @Author: wyy
 */
public interface UserService extends BaseService<User, Long> {

    public User findByUsername(String username);

}

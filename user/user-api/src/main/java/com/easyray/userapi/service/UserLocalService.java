package com.easyray.userapi.service;

import com.easyray.common.exception.EasyCustomException;
import com.easyray.common.exception.EntityNotExistException;
import com.easyray.common.exception.NoPermissionException;
import com.easyray.baseapi.service.BaseLocalService;
import com.easyray.userapi.entity.User;

/**
 * @Date: 20-1-26
 * @Author: wyy
 */
public interface UserLocalService extends BaseLocalService<User> {

    public User findByUsername(String username) throws EntityNotExistException;

    public User fetchByUsername(String username);

    public void testException() throws NoPermissionException, EasyCustomException, EntityNotExistException;

}

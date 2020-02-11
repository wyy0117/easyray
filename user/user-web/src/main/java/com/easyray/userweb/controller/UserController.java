package com.easyray.userweb.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.easyray.auth.service.impl.SpringSecurityThreadLocal;
import com.easyray.common.exception.EasyCustomException;
import com.easyray.common.exception.EntityNotExistException;
import com.easyray.common.exception.NoPermissionException;
import com.easyray.userapi.entity.User;
import com.easyray.userapi.service.UserLocalProvider;
import com.easyray.userapi.service.UserProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author easyray
 * @since 2020-01-26
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Reference
    private UserLocalProvider userLocalService;

    @Reference
    private UserProvider userService;

    @Autowired
    private SpringSecurityThreadLocal springSecurityThreadLocal;

    @GetMapping("/hello")
    public String hello() throws EntityNotExistException, NoPermissionException, EasyCustomException {
//        User user = userLocalService.fetchByUsername("456");
        User user = springSecurityThreadLocal.getUser();
        userLocalService.testException();
        return "hello";
    }
}


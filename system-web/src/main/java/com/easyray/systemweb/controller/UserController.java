package com.easyray.systemweb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.easyray.auth.service.impl.SpringSecurityThreadLocal;
import com.easyray.common.exception.EasyCustomException;
import com.easyray.common.exception.EntityNotExistException;
import com.easyray.systemapi.entity.User;
import com.easyray.systemapi.service.UserLocalProvider;
import com.easyray.systemapi.service.UserProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.NoPermissionException;

/**
 * @Date: 20-2-12
 * @Author: wyy
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Reference
    private UserLocalProvider userLocalProvider;

    @Reference
    private UserProvider userService;

    @Autowired
    private SpringSecurityThreadLocal springSecurityThreadLocal;

    @GetMapping("/hello")
    public String hello() throws EntityNotExistException, NoPermissionException, EasyCustomException {
//        User user = userLocalProvider.fetchByUsername("456");
        User user = springSecurityThreadLocal.getUser();
        return "hello";
    }
}

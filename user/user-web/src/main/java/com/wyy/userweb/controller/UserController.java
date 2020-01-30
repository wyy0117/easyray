package com.wyy.userweb.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.wyy.easyry.service.UserCheckPermission;
import com.wyy.easyry.service.UserLocalService;
import com.wyy.easyry.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Proxy;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wyy
 * @since 2020-01-26
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Reference
    private UserLocalService userLocalService;

    @Reference
    private UserService userService;

    @GetMapping("/hello")
    public String hello() {
//        userLocalService.findByUsername("123");
        userService.findByUsername("456");
        return "hello";
    }


}


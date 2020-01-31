package com.wyy.userweb.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.wyy.auth.annotation.EasyNoAuth;
import com.wyy.baseapi.exception.EasyCustomException;
import com.wyy.baseapi.exception.EntityNotExistException;
import com.wyy.baseapi.exception.NoPermissionException;
import com.wyy.easyry.service.UserLocalService;
import com.wyy.easyry.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @EasyNoAuth
    public String hello() throws EntityNotExistException, NoPermissionException, EasyCustomException {
//        User user = userLocalService.fetchByUsername("456");
        userLocalService.testException();
        return "hello";
    }
}


package com.easyray.userweb.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.easyray.common.exception.EasyCustomException;
import com.easyray.common.exception.EntityNotExistException;
import com.easyray.common.exception.NoPermissionException;
import com.easyray.userapi.service.UserLocalService;
import com.easyray.userapi.service.UserService;
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
    private UserLocalService userLocalService;

    @Reference
    private UserService userService;

    @GetMapping("/hello")
    public String hello() throws EntityNotExistException, NoPermissionException, EasyCustomException {
//        User user = userLocalService.fetchByUsername("456");
        userLocalService.testException();
        return "hello";
    }
}


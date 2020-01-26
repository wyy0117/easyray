package com.wyy.userweb.controller;


import com.alibaba.dubbo.config.annotation.Reference;
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
    private UserService userService;

    @GetMapping("/hello")
    public String hello() {
        userService.findByUsername("123");
        return "hello";
    }


}


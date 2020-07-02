package com.easyray.teamweb.controller;

import com.easyray.common.exception.EasyrayAbstractException;
import com.easyray.teamservice.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Date: 2020/7/2
 * @Author: wyy
 */
@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("test")
    public void test() throws EasyrayAbstractException {
        testService.test();
    }
}

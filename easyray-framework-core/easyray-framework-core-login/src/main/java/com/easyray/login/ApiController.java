package com.easyray.login;

import com.easyray.auth.annotation.EasyrayNoAuth;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Date: 2020/6/9
 * @Author: wyy
 */
@RestController
public class ApiController {

    private final Logger logger = LoggerFactory.getLogger(ApiController.class);

    @GetMapping("status")
    public void status(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("authentication = " + authentication);
    }

    @GetMapping("sms-code")
    @EasyrayNoAuth
    public String smsCode(@RequestParam String phone){
        String code = RandomStringUtils.randomNumeric(6);
        logger.debug("generate code for phone:{},code:{}",phone,code);
        return code;
    }

}

package com.wyy.userservice;

import com.wyy.easyry.service.UserLocalService;
import com.wyy.easyry.service.UserService;
import com.wyy.userservice.service.impl.UserLocalServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class UserLocalServiceApplicationTests {

    @Resource(type = UserLocalServiceImpl.class, name = "userLocalService")
    private UserLocalService userLocalService;

    @Autowired
    private UserService userService;

    @Test
    void addUser() {
//        User user = new User();
//        user.setId(1L);
//        user.setUsername("wyy");
//        user.setPassword("123");
//        user.setFullName("wyy");
//        userLocalService.save(user);

    }

    @Test
    void testApi() {
        userService.findByUsername("123");
    }


}

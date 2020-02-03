package com.easyray.userservice;

import com.easyray.userapi.service.UserLocalService;
import com.easyray.userapi.service.UserService;
import com.easyray.userservice.service.impl.UserLocalServiceImpl;
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
//        user.setUsername("easyray");
//        user.setPassword("123");
//        user.setFullName("easyray");
//        userLocalService.save(user);

    }

    @Test
    void testApi() {
    }


}

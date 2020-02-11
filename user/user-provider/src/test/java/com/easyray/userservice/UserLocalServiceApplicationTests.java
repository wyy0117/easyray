package com.easyray.userservice;

import com.easyray.userapi.service.UserLocalProvider;
import com.easyray.userapi.service.UserProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserLocalServiceApplicationTests {

    @Autowired
    @Qualifier("userLocalProviderImpl")
    private UserLocalProvider userLocalService;

    @Autowired
    private UserProvider userService;

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

package com.easyray.systemprovider;

import com.easyray.systemapi.entity.User;
import com.easyray.systemapi.service.UserProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Date: 20-2-22
 * @Author: wyy
 */
@SpringBootTest
public class AOPTest {

    @Autowired
    private UserProvider userProvider;

    @Test
    public void testAOP() {
//        userProvider.fetchByUsername("123");
        userProvider.a(1,new User(1L));
    }
}

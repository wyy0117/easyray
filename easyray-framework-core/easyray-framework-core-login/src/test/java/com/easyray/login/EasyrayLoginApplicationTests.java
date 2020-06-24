package com.easyray.login;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//@SpringBootTest
class EasyrayLoginApplicationTests {

    @Test
    void contextLoads() {
        String admin = new String("admin");
//		String encodedpwd = new BCryptPasswordEncoder().encode("admin");
//		System.out.println("encodedpwd = " + encodedpwd);
        assert new BCryptPasswordEncoder().matches(admin, "$2a$10$IgdsgVk7lR6pwTus9V7zj.TRnQ5liWcDa7VJZtJfi62fmbjeQajle");

    }

}

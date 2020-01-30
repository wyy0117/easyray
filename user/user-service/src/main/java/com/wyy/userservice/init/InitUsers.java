package com.wyy.userservice.init;

import com.wyy.easyry.entity.User;
import com.wyy.easyry.service.UserLocalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @Date: 20-1-30
 * @Author: wyy
 */
@Component
public class InitUsers implements ApplicationRunner {

    private Logger log = LoggerFactory.getLogger(InitUsers.class);

    @Value("${admin.username}")
    private String username;
    @Value("${admin.password}")
    private String password;

    @Autowired
    private UserLocalService userLocalService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        log.debug("init users");

        User admin = userLocalService.fetchByUsername(username);
        if (admin == null) {
            log.debug("add user {}", username);
            User user = new User();
            user.setUsername(username);
            user.setFullName(username);
            user.setPassword(bCryptPasswordEncoder.encode(password));
            userLocalService.save(user);
        }
    }
}

package com.easyray.userservice.init;

import com.easyray.baseapi.idgenerator.service.IdService;
import com.easyray.baseapi.init.IEasyIniter;
import com.easyray.userapi.entity.User;
import com.easyray.userapi.service.UserLocalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @Date: 20-1-30
 * @Author: wyy
 */
@Component
public class UsersIniter implements IEasyIniter {

    private Logger log = LoggerFactory.getLogger(UsersIniter.class);

    @Value("${admin.username}")
    private String username;
    @Value("${admin.password}")
    private String password;

    @Autowired
    private UserLocalService userLocalService;
    @Autowired
    private IdService idService;

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public void init(ApplicationArguments args) {
        log.debug("init users");

        User admin = userLocalService.fetchByUsername(username);
        if (admin == null) {
            log.debug("add user {}", username);
            User user = new User(idService.nextId(User.class.getName()));
            user.setUsername(username);
            user.setFullName(username);
            user.setPassword(new BCryptPasswordEncoder().encode(password));
            userLocalService.save(user);
        }
    }
}

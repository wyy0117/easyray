package com.easyray.userservice.init;

import com.easyray.baseapi.idgenerator.service.IdService;
import com.easyray.baseapi.init.IEasyIniter;
import com.easyray.userapi.entity.User;
import com.easyray.userapi.service.UserLocalService;
import com.easyray.userservice.autoconfig.UserConfigurationProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private UserConfigurationProperties userConfigurationProperties;

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

        User admin = userLocalService.fetchByUsername(userConfigurationProperties.getUsername());
        if (admin == null) {
            log.debug("add user {}", userConfigurationProperties.getUsername());
            User user = new User(idService.nextId(User.class.getName()));
            user.setUsername(userConfigurationProperties.getUsername());
            user.setFullName(userConfigurationProperties.getUsername());
            user.setPassword(new BCryptPasswordEncoder().encode(userConfigurationProperties.getPassword()));
            userLocalService.save(user);
        }
    }
}

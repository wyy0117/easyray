package com.easyray.userservice.init;

import com.alibaba.dubbo.config.annotation.Reference;
import com.easyray.baseapi.constant.InitOrderConstant;
import com.easyray.baseapi.init.IEasyInit;
import com.easyray.idgeneratorapi.service.IdService;
import com.easyray.userapi.entity.User;
import com.easyray.userapi.service.UserLocalProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Date: 20-1-30
 * @Author: wyy
 */
@Component
public class InitUsers implements IEasyInit {

    private Logger log = LoggerFactory.getLogger(InitUsers.class);

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    @Qualifier("userLocalProviderImpl")
    private UserLocalProvider userLocalService;
    @Reference
    private IdService idService;

    @Override
    public int getOrder() {
        return InitOrderConstant.INIT_USERS;
    }

    @Override
    public void init(ApplicationArguments args) throws Exception {
        log.debug("init users");

        User admin = userLocalService.fetchByUsername(securityProperties.getUser().getName());
        if (admin == null) {
            log.debug("add user {}", securityProperties.getUser().getName());
            User user = new User(idService.nextId(User.class.getName()));
            user.setUsername(securityProperties.getUser().getName());
            user.setFullName(securityProperties.getUser().getName());
            user.setPassword(new BCryptPasswordEncoder().encode(securityProperties.getUser().getPassword()));
            user.setCreateDate(new Date());
            userLocalService.save(user);
        }
    }
}

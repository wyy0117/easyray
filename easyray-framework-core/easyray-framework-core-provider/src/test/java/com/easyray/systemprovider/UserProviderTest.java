package com.easyray.systemprovider;

import com.easyray.common.exception.EasyrayAbstractException;
import com.easyray.coreapi.entity.User;
import com.easyray.idgeneratorapi.provider.IdService;
import com.easyray.systemprovider.provider.impl.UserLocalProviderImpl;
import org.apache.dubbo.config.annotation.DubboReference;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Date: 2020/6/6
 * @Author: wyy
 */
@SpringBootTest
public class UserProviderTest {
    private Logger logger = LoggerFactory.getLogger(UserProviderTest.class);

    @DubboReference
    private IdService idService;

    @Autowired
    private UserLocalProviderImpl userLocalProviderImpl;

    @Test
    public void testAddUser() throws EasyrayAbstractException {
        User user = new User(idService.nextId(User.class.getName()));
        user.setUsername(System.currentTimeMillis() + "")
                .setPasswordAndEncode("123456");
        user.setUserId(user.getId())
                .setFullName(user.getUsername());
        userLocalProviderImpl.add(user);
    }
}

package com.easyray.systemprovider;

import com.alibaba.dubbo.config.annotation.Reference;
import com.easyray.idgeneratorapi.provider.IdService;
import com.easyray.systemapi.entity.Group;
import com.easyray.systemapi.entity.User;
import com.easyray.systemapi.service.GroupLocalProvider;
import com.easyray.systemapi.service.UserLocalProvider;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class SystemProviderApplicationTests {
    private Logger log = LoggerFactory.getLogger(SystemProviderApplication.class);

    @Autowired
    @Qualifier("userLocalProviderImpl")
    private UserLocalProvider userLocalProvider;

    @Autowired
    @Qualifier("groupLocalProviderImpl")
    private GroupLocalProvider groupLocalProvider;

    @Reference
    private IdService idService;

    @Test
    void addUser() {
        User user = doAddUser();
    }

    private User doAddUser() {
        User user = new User(idService.nextId(User.class.getName()))
//                .setUsername(System.currentTimeMillis() + "")
                .setUsername("test")
                .setPassword("test");
        user.setUserId(user.getId())
                .setFullName("test");
//                .setCreateDate(new Date());
        log.debug("user = " + user);
        userLocalProvider.save(user);
        return user;
    }

    @Test
    void addGroup() {
        User user = doAddUser();
        Group group = doAddGroup(user);
    }

    private Group doAddGroup(User user) {
        Group group = new Group(idService.nextId(Group.class.getName()))
                .setName(System.currentTimeMillis() + "");
        group.setUserId(user.getId())
                .setFullName(user.getFullName())
                .setCreateDate(new Date());
        groupLocalProvider.save(group);
        log.debug("group = " + group);
        return group;
    }

    @Test
    void testFetchUser() {
        User user = doAddUser();
        User user1 = userLocalProvider.fetchByUsername(user.getUsername());
        assert user.getId().equals(user1.getId());
        userLocalProvider.removeById(user.getId());
    }

}

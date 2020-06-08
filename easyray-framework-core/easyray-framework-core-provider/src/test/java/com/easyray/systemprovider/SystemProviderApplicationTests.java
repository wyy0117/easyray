package com.easyray.systemprovider;

import com.alibaba.dubbo.config.annotation.Reference;
import com.easyray.coreapi.entity.Tenant;
import com.easyray.coreapi.entity.User;
import com.easyray.coreapi.service.TenantLocalProvider;
import com.easyray.coreapi.service.UserLocalProvider;
import com.easyray.idgeneratorapi.provider.IdService;
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
    @Qualifier("tenantLocalProviderImpl")
    private TenantLocalProvider tenantLocalProvider;

    @Reference
    private IdService idService;

    @Test
    void addUser() {
        User user = doAddUser();
    }

    private User doAddUser() {
        User user = new User(idService.nextId(User.class.getName()))
//                .setUsername(System.currentTimeMillis() + "")
                .setUsername("test2")
                .setPasswordAndEncode("test");
        user.setUserId(user.getId())
                .setFullName("test");
//                .setCreateDate(new Date());
        log.debug("user = " + user);
        userLocalProvider.save(user);
        return user;
    }

    @Test
    void addTenant() {
        User user = doAddUser();
        Tenant tenant = doAddTenant(user);
    }

    private Tenant doAddTenant(User user) {
        Tenant tenant = new Tenant(idService.nextId(Tenant.class.getName()))
                .setName(System.currentTimeMillis() + "");
        tenant.setUserId(user.getId())
                .setFullName(user.getFullName())
                .setCreateDate(new Date());
        tenantLocalProvider.save(tenant);
        log.debug("tenant = " + tenant);
        return tenant;
    }

    @Test
    void testFetchUser() {
        User user = doAddUser();
        User user1 = userLocalProvider.fetchByUsername(user.getUsername());
        assert user.getId().equals(user1.getId());
        userLocalProvider.removeById(user.getId());
    }

}

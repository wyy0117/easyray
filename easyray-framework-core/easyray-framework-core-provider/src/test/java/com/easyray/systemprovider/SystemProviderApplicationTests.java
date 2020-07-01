package com.easyray.systemprovider;

import com.easyray.common.exception.EasyrayAbstractException;
import com.easyray.coreapi.entity.Tenant;
import com.easyray.coreapi.entity.User;
import com.easyray.coreapi.service.TenantLocalProvider;
import com.easyray.coreapi.service.UserLocalProvider;
import com.easyray.idgeneratorapi.provider.IdService;
import org.apache.dubbo.config.annotation.DubboReference;
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

    @DubboReference
    private IdService idService;

    @Test
    void addUser() throws EasyrayAbstractException {
        User user = doAddUser();
    }

    private User doAddUser() throws EasyrayAbstractException {
        User user = new User(idService.nextId(User.class.getName()))
//                .setUsername(System.currentTimeMillis() + "")
                .setUsername("test2")
                .setPasswordAndEncode("test");
        user.setUserId(user.getId())
                .setFullName("test");
//                .setCreateDate(new Date());
        log.debug("user = " + user);
        userLocalProvider.add(user);
        return user;
    }

    @Test
    void addTenant() throws EasyrayAbstractException {
        User user = doAddUser();
        Tenant tenant = doAddTenant(user);
    }

    private Tenant doAddTenant(User user) throws EasyrayAbstractException {
        Tenant tenant = new Tenant(idService.nextId(Tenant.class.getName()))
                .setName(System.currentTimeMillis() + "");
        tenant.setUserId(user.getId())
                .setFullName(user.getFullName())
                .setCreateDate(new Date());
        tenantLocalProvider.add(tenant);
        log.debug("tenant = " + tenant);
        return tenant;
    }

    @Test
    void testFetchUser() throws EasyrayAbstractException {
        User user = doAddUser();
        User user1 = userLocalProvider.fetchByUsername(user.getUsername());
        assert user.getId().equals(user1.getId());
        userLocalProvider.delete(user.getId());
    }

}

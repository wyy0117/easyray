package com.easyray.systemprovider;

import com.alibaba.dubbo.config.annotation.Reference;
import com.easyray.auth.service.impl.SpringSecurityUtil;
import com.easyray.baseapi.constant.RoleTypeConstant;
import com.easyray.coreapi.entity.Role;
import com.easyray.coreapi.entity.User;
import com.easyray.idgeneratorapi.provider.IdService;
import com.easyray.systemprovider.provider.impl.RoleLocalProviderImpl;
import com.easyray.systemprovider.provider.impl.UserLocalProviderImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Date: 2020/6/6
 * @Author: wyy
 */
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RoleProviderTest {

    private Logger logger = LoggerFactory.getLogger(RoleProviderTest.class);

    @Reference
    private IdService idService;

    @Autowired
    private RoleLocalProviderImpl roleLocalProviderImpl;
    @Autowired
    private UserLocalProviderImpl userLocalProviderImpl;

    @Autowired
    private SpringSecurityUtil springSecurityUtil;

    @BeforeAll
    public void before() {
        User user = userLocalProviderImpl.fetchByUsername("admin");
        springSecurityUtil.setUser(user);
    }

    @Test
    public void testAddRole() {
        long roleId = idService.nextId(Role.class.getName());
        Role role = new Role(roleId);
        role.setName("RANGE_ROLE")
                .setType(RoleTypeConstant.RANGE_ROLE);
        roleLocalProviderImpl.save(role);
    }
}

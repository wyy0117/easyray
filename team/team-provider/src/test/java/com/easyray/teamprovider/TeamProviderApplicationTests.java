package com.easyray.teamprovider;

import com.alibaba.dubbo.config.annotation.Reference;
import com.easyray.auth.service.impl.SpringSecurityThreadLocal;
import com.easyray.auth.service.impl.UserDetailServiceImpl;
import com.easyray.common.exception.EntityNotExistException;
import com.easyray.resourcepermission.entity.ResourcePermission;
import com.easyray.resourcepermission.service.ResourcePermissionLocalProvider;
import com.easyray.systemapi.entity.User;
import com.easyray.systemapi.service.UserLocalProvider;
import com.easyray.teamapi.entity.Team;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TeamProviderApplicationTests {

    @Autowired
    private SpringSecurityThreadLocal springSecurityThreadLocal;

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Reference
    private UserLocalProvider userLocalProvider;

    @Autowired
    private ResourcePermissionLocalProvider resourcePermissionLocalProvider;

    @BeforeAll
    public void before() throws EntityNotExistException {
        User user = userLocalProvider.findByUsername("test");

        springSecurityThreadLocal.setUser(user);
    }

    @Test
    public void test() {
        User user = springSecurityThreadLocal.getUser();
        assert user != null;
    }

    @Test
    public void testPermission() {
        User user = springSecurityThreadLocal.getUser();
        List<ResourcePermission> resourcePermissionList = resourcePermissionLocalProvider.findResourcePermission(user.getId(), 123, "VIEW", Team.class.getName());
        System.out.println("resourcePermissionList.size() = " + resourcePermissionList.size());
    }

}

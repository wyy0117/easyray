package com.easyray.roleprovider;

import com.alibaba.dubbo.config.annotation.Reference;
import com.easyray.idgeneratorapi.service.IdService;
import com.easyray.roleapi.constant.RoleTypeConstant;
import com.easyray.roleapi.entity.Role;
import com.easyray.roleapi.provider.RoleLocalProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class RoleServiceApplicationTests {

    @Autowired
    @Qualifier("roleLocalProviderImpl")
    private RoleLocalProvider roleLocalProvider;

    @Reference
    private IdService idService;

    @Test
    public void addRole() {
        Role role = new Role(idService.nextId(Role.class.getName()));
        role.setUserId(0)
                .setFullName("")
                .setCreateDate(new Date());
        role.setName("组长")
                .setType(RoleTypeConstant.RANGE_ROLE);
        roleLocalProvider.save(role);
    }

}

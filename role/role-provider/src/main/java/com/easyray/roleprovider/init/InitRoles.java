package com.easyray.roleprovider.init;

import com.alibaba.dubbo.config.annotation.Reference;
import com.easyray.baseapi.constant.InitOrderConstant;
import com.easyray.baseapi.init.IEasyInit;
import com.easyray.idgeneratorapi.service.IdService;
import com.easyray.roleapi.constant.RoleNameConstant;
import com.easyray.roleapi.entity.Role;
import com.easyray.roleapi.provider.RoleLocalProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Date: 20-2-8
 * @Author: wyy
 */
@Component
@Transactional
public class InitRoles implements IEasyInit {

    private String[] roles = {RoleNameConstant.ADMINISTRATOR};
    @Autowired
    @Qualifier("roleLocalProviderImpl")
    private RoleLocalProvider roleLocalService;
    @Reference
    private IdService idService;

    @Override
    public int getOrder() {
        return InitOrderConstant.INIT_ROLE;
    }

    @Override
    public void init(ApplicationArguments args) throws Exception {
        for (String roleName : roles) {
            Role role = roleLocalService.fetchByName(roleName);
            if (role == null) {
                role = new Role(idService.nextId(Role.class.getName()));
                role.setName(roleName)
                        .setUserId(0)
                        .setFullName("system")
                        .setCreateDate(new Date())
                        .setModifiedDate(new Date());
                roleLocalService.save(role);
            }
        }
    }
}

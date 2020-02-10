package com.easyray.roleservice.init;

import com.alibaba.dubbo.config.annotation.Reference;
import com.easyray.baseapi.constant.InitOrderConstant;
import com.easyray.baseapi.init.IEasyInit;
import com.easyray.idgenerator.service.IdService;
import com.easyray.roleapi.constant.RoleNameConstant;
import com.easyray.roleapi.entity.Role;
import com.easyray.roleapi.service.RoleLocalService;
import com.easyray.userapi.service.UserLocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Date: 20-2-8
 * @Author: wyy
 */
@Component
public class InitRoles implements IEasyInit {

    private String[] roles = {RoleNameConstant.ADMINISTRATOR};
    @Autowired
    private RoleLocalService roleLocalService;
    @Autowired
    private IdService idService;
    @Reference(check = false)
    private UserLocalService userLocalService;

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
                        .setFullName("")
                        .setCreateDate(new Date())
                        .setModifiedDate(new Date());
                roleLocalService.save(role);
            } else {
                if (role.getUserId() == 0) {
//                    User admin = userLocalService.findByUsername(this.admin);
//                    role.setUserId(admin.getUserId());
//                    roleLocalService.saveOrUpdate(role);
                }
            }
        }
    }
}

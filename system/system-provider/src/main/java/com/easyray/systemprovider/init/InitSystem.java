package com.easyray.systemprovider.init;

import com.alibaba.dubbo.config.annotation.Reference;
import com.easyray.baseapi.constant.InitOrderConstant;
import com.easyray.baseapi.init.IEasyInit;
import com.easyray.idgeneratorapi.provider.IdService;
import com.easyray.baseapi.constant.RoleNameConstant;
import com.easyray.baseapi.constant.RoleTypeConstant;
import com.easyray.systemapi.entity.Group;
import com.easyray.systemapi.entity.Role;
import com.easyray.systemapi.entity.User;
import com.easyray.systemapi.entity.UserRole;
import com.easyray.systemapi.service.GroupLocalProvider;
import com.easyray.systemapi.service.RoleLocalProvider;
import com.easyray.systemapi.service.UserLocalProvider;
import com.easyray.systemapi.service.UserRoleLocalProvider;
import com.easyray.systemprovider.autoconfig.SystemGroupProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Date: 20-2-8
 * @Author: wyy
 */
@Component
@Transactional
public class InitSystem implements IEasyInit {
    private Logger log = LoggerFactory.getLogger(InitSystem.class.getName());

    @Autowired
    @Qualifier("userLocalProviderImpl")
    private UserLocalProvider userLocalProvider;

    @Autowired
    @Qualifier("roleLocalProviderImpl")
    private RoleLocalProvider roleLocalProvider;
    @Reference
    private IdService idService;
    @Autowired
    @Qualifier("userRoleLocalProviderImpl")
    private UserRoleLocalProvider userRoleLocalProvider;
    @Autowired
    @Qualifier("groupLocalProviderImpl")
    private GroupLocalProvider groupLocalProvider;

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private SystemGroupProperties systemGroupProperties;

    @Override
    public int getOrder() {
        return InitOrderConstant.INIT_SYSTEM;
    }

    @Override
    public void init(ApplicationArguments args) throws Exception {
        /**
         * 初始化角色 {@link RoleNameConstant#USER_ROLE_NAME}
         */
        String roleName = RoleNameConstant.USER_ROLE_NAME;
        Role role = roleLocalProvider.fetchByName(roleName);
        boolean updateRole = false;
        if (role == null) {
            role = new Role(idService.nextId(Role.class.getName()));
            role.setName(roleName)
                    .setType(RoleTypeConstant.GLOBAL_ROLE)
                    .setUserId(0)
                    .setFullName("")
                    .setCreateDate(new Date())
                    .setModifiedDate(new Date());
            roleLocalProvider.save(role);
            updateRole = true;
        }
        //初始化user
        User user = initUser();
        if (updateRole) {
            //修改角色的用户信息
            role.setUserId(user.getId())
                    .setFullName(user.getFullName());
            roleLocalProvider.saveOrUpdate(role);
        }
        /**
         * 初始化角色 {@link RoleNameConstant#ADMINISTRATOR_ROLE_NAME}
         */
        role = initRole(RoleNameConstant.ADMINISTRATOR_ROLE_NAME, user, RoleTypeConstant.GLOBAL_ROLE);
        initUserRole(role, user);

        /**
         * 初始化角色{@link RoleNameConstant#GROUP_OWNER_ROLE_NAME}
         */
        role = initRole(RoleNameConstant.GROUP_OWNER_ROLE_NAME, user, RoleTypeConstant.GROUP_ROLE);

        initGroup(systemGroupProperties.getName(), user);
    }

    private Role initRole(String roleName, User user, int roleType) {
        Role role = roleLocalProvider.fetchByName(roleName);
        if (role == null) {
            role = new Role(idService.nextId(Role.class.getName()));
            role.setName(roleName)
                    .setType(roleType)
                    .setUserId(user.getId())
                    .setFullName(user.getFullName())
                    .setCreateDate(new Date())
                    .setModifiedDate(new Date());
            roleLocalProvider.save(role);
        }
        return role;
    }

    private void initUserRole(Role role, User user) {
        UserRole userRole = new UserRole(idService.nextId(UserRole.class.getName()), user.getId(), role.getId());
        userRoleLocalProvider.save(userRole);
    }

    private void initGroup(String groupName, User user) {
        Group group = groupLocalProvider.fetchByName(groupName);
        if (group == null) {
            group = new Group(idService.nextId(Group.class.getName()))
                    .setName(groupName);
            group.setUserId(user.getId())
                    .setFullName(user.getFullName())
                    .setCreateDate(new Date())
                    .setModifiedDate(new Date());
            groupLocalProvider.save(group);
        }
    }

    private User initUser() {
        log.debug("init user");
        User admin = userLocalProvider.fetchByUsername(securityProperties.getUser().getName());
        if (admin == null) {
            log.debug("add user {}", securityProperties.getUser().getName());
            admin = new User(idService.nextId(User.class.getName()));
            admin.setUsername(securityProperties.getUser().getName());
            admin.setFullName(securityProperties.getUser().getName());
            admin.setPassword(new BCryptPasswordEncoder().encode(securityProperties.getUser().getPassword()));
            admin.setCreateDate(new Date());
            userLocalProvider.save(admin);
        }
        return admin;

    }
}

package com.easyray.systemprovider.init;

import com.easyray.baseapi.constant.InitOrderConstant;
import com.easyray.baseapi.constant.RoleNameConstant;
import com.easyray.baseapi.constant.RoleTypeConstant;
import com.easyray.common.exception.EasyrayAbstractException;
import com.easyray.coreapi.entity.Role;
import com.easyray.coreapi.entity.Tenant;
import com.easyray.coreapi.entity.User;
import com.easyray.coreapi.entity.UserRole;
import com.easyray.coreapi.service.RoleLocalProvider;
import com.easyray.coreapi.service.TenantLocalProvider;
import com.easyray.coreapi.service.UserLocalProvider;
import com.easyray.coreapi.service.UserRoleLocalProvider;
import com.easyray.extension.init.IEasyrayInit;
import com.easyray.idgeneratorapi.provider.IdService;
import com.easyray.systemprovider.autoconfig.SystemTenantProperties;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Date: 20-2-8
 * @Author: wyy
 */
@Component
@Transactional
public class InitSystem implements IEasyrayInit {
    private Logger log = LoggerFactory.getLogger(InitSystem.class.getName());

    @Autowired
    @Qualifier("userLocalProviderImpl")
    private UserLocalProvider userLocalProvider;

    @Autowired
    @Qualifier("roleLocalProviderImpl")
    private RoleLocalProvider roleLocalProvider;
    @DubboReference
    private IdService idService;
    @Autowired
    @Qualifier("userRoleLocalProviderImpl")
    private UserRoleLocalProvider userRoleLocalProvider;
    @Autowired
    @Qualifier("tenantLocalProviderImpl")
    private TenantLocalProvider tenantLocalProvider;

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private SystemTenantProperties systemTenantProperties;

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
        long adminUserId = 0;

        if (role == null) {
            adminUserId = idService.nextId(User.class.getName());
            role = new Role(idService.nextId(Role.class.getName()));
            role.setName(roleName)
                    .setType(RoleTypeConstant.GLOBAL_ROLE)
                    .setUserId(adminUserId)
                    .setFullName(securityProperties.getUser().getName())
                    .setCreateDate(new Date())
                    .setModifiedDate(new Date());
            roleLocalProvider.add(role);
        }

        User user = userLocalProvider.fetchByUsername(securityProperties.getUser().getName());
        if (user == null) {
            log.debug("init user");
            log.debug("add user {}", securityProperties.getUser().getName());
            adminUserId = idService.nextId(User.class.getName());
            user = new User(adminUserId);
            user.setUsername(securityProperties.getUser().getName());
            user.setFullName(securityProperties.getUser().getName());
            user.setPasswordAndEncode(securityProperties.getUser().getPassword());
            user.setCreateDate(new Date());
            userLocalProvider.add(user);
        }

        List<User> userRoleList = userRoleLocalProvider.findUserByRoleId(role.getId());
        if (userRoleList.size() == 0) {
            initUserRole(role, user);
        }

        /**
         * 初始化角色 {@link RoleNameConstant#ADMINISTRATOR_ROLE_NAME}
         */
        roleName = RoleNameConstant.ADMINISTRATOR_ROLE_NAME;
        role = roleLocalProvider.fetchByName(roleName);
        if (role == null) {
            role = new Role(idService.nextId(Role.class.getName()));
            role.setName(roleName)
                    .setType(RoleTypeConstant.GLOBAL_ROLE)
                    .setUserId(user.getId())
                    .setFullName(user.getFullName())
                    .setCreateDate(new Date())
                    .setModifiedDate(new Date());
            roleLocalProvider.add(role);
        }
        userRoleList = userRoleLocalProvider.findUserByRoleId(role.getId());
        if (userRoleList.size() == 0) {
            initUserRole(role, user);
        }

        /**
         * 初始化角色{@link RoleNameConstant#TENANT_OWNER_ROLE_NAME}
         */
        roleName = RoleNameConstant.TENANT_OWNER_ROLE_NAME;
        role = roleLocalProvider.fetchByName(roleName);
        if (role == null) {
            role = new Role(idService.nextId(Role.class.getName()));
            role.setName(roleName)
                    .setType(RoleTypeConstant.TENANT_ROLE)
                    .setUserId(user.getId())
                    .setFullName(user.getFullName())
                    .setCreateDate(new Date())
                    .setModifiedDate(new Date());
            roleLocalProvider.add(role);
        }

        initTenant(systemTenantProperties.getName(), user);
    }

    private void initUserRole(Role role, User user) throws EasyrayAbstractException {
        UserRole userRole = new UserRole(idService.nextId(UserRole.class.getName()), user.getId(), role.getId());
        userRoleLocalProvider.add(userRole);
    }

    private void initTenant(String tenantName, User user) throws EasyrayAbstractException {
        Tenant tenant = tenantLocalProvider.fetchByName(tenantName);
        if (tenant == null) {
            tenant = new Tenant(idService.nextId(Tenant.class.getName()))
                    .setName(tenantName);
            tenant.setUserId(user.getId())
                    .setFullName(user.getFullName())
                    .setCreateDate(new Date())
                    .setModifiedDate(new Date());
            tenantLocalProvider.add(tenant);
        }
    }

}

package com.easyray.teamprovider;

import com.alibaba.dubbo.config.annotation.Reference;
import com.easyray.auth.service.impl.SpringSecurityThreadLocal;
import com.easyray.auth.service.impl.UserDetailServiceImpl;
import com.easyray.baseapi.constant.ActionScopeConstant;
import com.easyray.common.exception.EntityNotExistException;
import com.easyray.coreapi.entity.*;
import com.easyray.coreapi.service.*;
import com.easyray.idgeneratorapi.provider.IdService;
import com.easyray.resourcepermission.entity.ResourceAction;
import com.easyray.resourcepermission.entity.ResourcePermission;
import com.easyray.resourcepermission.service.ResourceActionLocalProvider;
import com.easyray.resourcepermission.service.ResourcePermissionLocalProvider;
import com.easyray.resourcepermission.util.PermissionUtil;
import com.easyray.teamapi.entity.Team;
import com.easyray.teamapi.service.TeamLocalProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TeamProviderApplicationTests {

    @Autowired
    private SpringSecurityThreadLocal springSecurityThreadLocal;

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Autowired
    @Qualifier("teamLocalProviderImpl")
    private TeamLocalProvider teamLocalProvider;

    @Reference
    private UserLocalProvider userLocalProvider;

    @Autowired
    private ResourcePermissionLocalProvider resourcePermissionLocalProvider;

    @Reference
    private IdService idService;

    @Reference
    private RoleLocalProvider roleLocalProvider;

    @Autowired
    private PermissionUtil permissionUtil;

    @Reference
    private UserRoleLocalProvider userRoleLocalProvider;

    @Reference
    private TenantLocalProvider tenantLocalProvider;

    @Reference
    private UserTenantRoleLocalProvider userTenantRoleLocalProvider;

    @Autowired
    private ResourceActionLocalProvider resourceActionLocalProvider;

    private User initSpringSecurityThreadLocal(String fullName) throws EntityNotExistException {
        User user = userLocalProvider.findByUsername(fullName);

        springSecurityThreadLocal.setUser(user);
        return user;
    }

    @Test
    public void test() {
        User user = springSecurityThreadLocal.getUser();
        assert user != null;
    }

    @Test
    public void testAdd() {
        Team team = new Team(idService.nextId(Team.class.getName()));
        team.setName(System.currentTimeMillis() + "")
                .setTenantId(3);
        teamLocalProvider.save(team);
    }

    @Test
    public void testPermission1() throws EntityNotExistException {
        //admin add role
        User user = initSpringSecurityThreadLocal("admin");

        //create user
        user = new User(idService.nextId(User.class.getName()));
        user.setUsername(System.currentTimeMillis() + "")
                .setPasswordAndEncode("123456");
        user.setUserId(user.getId())
                .setFullName(user.getUsername());
        userLocalProvider.save(user);

        //don't have AA action
        assert !permissionUtil.havePermission(user.getId(), 0L, "AA", Team.class.getName());

        //add user role
        Role role = roleLocalProvider.findByName("GLOBAL_ROLE");
        UserRole userRole = new UserRole(idService.nextId(UserRole.class.getName()));
        userRole.setUserId(user.getId()).setRoleId(role.getId());
        userRoleLocalProvider.save(userRole);

        userRoleLocalProvider.removeById(userRole.getId());
        userLocalProvider.removeById(user.getId());

        //have AA action
        assert permissionUtil.havePermission(user.getId(), 0L, "AA", Team.class.getName());
    }

    @Test
    public void testPermission2() throws EntityNotExistException {
        //create tenant creator
        User tenantCreator = new User(idService.nextId(User.class.getName()));
        tenantCreator.setUsername(System.currentTimeMillis() + "")
                .setPasswordAndEncode("123456");
        tenantCreator.setUserId(tenantCreator.getId())
                .setFullName(tenantCreator.getUsername());
        userLocalProvider.save(tenantCreator);

        //create tenant
        initSpringSecurityThreadLocal(tenantCreator.getUsername());
        Tenant tenant = new Tenant(idService.nextId(Tenant.class.getName()));
        tenant.setName(System.currentTimeMillis() + "");
        tenantLocalProvider.save(tenant);
//创建普通用户
        User user = new User(idService.nextId(User.class.getName()));
        user.setUsername(System.currentTimeMillis() + "")
                .setPasswordAndEncode("123456");
        user.setUserId(user.getId())
                .setFullName(user.getUsername());
        userLocalProvider.save(user);
//添加用户到tenant中
        Role role = roleLocalProvider.findByName("USER");
        UserTenantRole userTenantRole1 = new UserTenantRole(idService.nextId(UserTenantRole.class.getName()), user.getId(), tenant.getId(), role.getId());
        userTenantRoleLocalProvider.save(userTenantRole1);
        //检查 无权限
        assert !permissionUtil.havePermission(user.getId(), tenant.getId(), "CC", Team.class.getName());

        //给用户TENANT_ROLE 角色
        role = roleLocalProvider.findByName("TENANT_ROLE");
        UserTenantRole userTenantRole2 = new UserTenantRole(idService.nextId(UserTenantRole.class.getName()), user.getId(), tenant.getId(), role.getId());
        userTenantRoleLocalProvider.save(userTenantRole2);

//检查 有权限
        assert permissionUtil.havePermission(user.getId(), tenant.getId(), "CC", Team.class.getName());

        //清理数据
        userTenantRoleLocalProvider.removeByIds(Arrays.asList(userTenantRole1.getId(), userTenantRole2.getId()));
        tenantLocalProvider.removeById(tenant.getId());
        userLocalProvider.removeByIds(Arrays.asList(tenantCreator.getId(), user.getId()));
    }

    @Test
    public void testPermission3() throws EntityNotExistException {
        //create tenant creator
        User tenantCreator = new User(idService.nextId(User.class.getName()));
        tenantCreator.setUsername(System.currentTimeMillis() + "")
                .setPasswordAndEncode("123456");
        tenantCreator.setUserId(tenantCreator.getId())
                .setFullName(tenantCreator.getUsername());
        userLocalProvider.save(tenantCreator);

        //create tenant
        initSpringSecurityThreadLocal(tenantCreator.getUsername());
        Tenant tenant = new Tenant(idService.nextId(Tenant.class.getName()));
        tenant.setName(System.currentTimeMillis() + "");
        tenantLocalProvider.save(tenant);
//创建普通用户
        User user = new User(idService.nextId(User.class.getName()));
        user.setUsername(System.currentTimeMillis() + "")
                .setPasswordAndEncode("123456");
        user.setUserId(user.getId())
                .setFullName(user.getUsername());
        userLocalProvider.save(user);
//添加用户到tenant中
        Role role = roleLocalProvider.findByName("USER");
        UserTenantRole userTenantRole1 = new UserTenantRole(idService.nextId(UserTenantRole.class.getName()), user.getId(), tenant.getId(), role.getId());
        userTenantRoleLocalProvider.save(userTenantRole1);

        //给范围角色赋BB的权限
        ResourceAction resourceAction = resourceActionLocalProvider.fetchByNameAndAction(Team.class.getName(), "BB");
        Role rangeRole = roleLocalProvider.findByName("RANGE_ROLE");
        ResourcePermission resourcePermission = new ResourcePermission(idService.nextId(ResourcePermission.class.getName()));
        resourcePermission.setName(Team.class.getName())
                .setScope(ActionScopeConstant.TENANT)
                .setRoleId(rangeRole.getId())
                .setActionIds(resourceAction.getBitwiseValue());
        resourcePermissionLocalProvider.save(resourcePermission);

        //检查 无权限
        assert !permissionUtil.havePermission(user.getId(), tenant.getId(), "BB", Team.class.getName());

        //给用户TENANT_ROLE 角色
        UserTenantRole userTenantRole2 = new UserTenantRole(idService.nextId(UserTenantRole.class.getName()), user.getId(), tenant.getId(), rangeRole.getId());
        userTenantRoleLocalProvider.save(userTenantRole2);

//检查 有权限
        assert permissionUtil.havePermission(user.getId(), tenant.getId(), "BB", Team.class.getName());

        //清理数据
        userTenantRoleLocalProvider.removeByIds(Arrays.asList(userTenantRole1.getId(), userTenantRole2.getId()));
        tenantLocalProvider.removeById(tenant.getId());
        userLocalProvider.removeByIds(Arrays.asList(tenantCreator.getId(), user.getId()));
    }

    @Test
    public void testTeamPermission() {
        Team team = new Team(idService.nextId(Team.class.getName()));
        team.setName(System.currentTimeMillis() + "")
                .setTenantId(313002);
        teamLocalProvider.save(team);

        //给团队的角色赋权限
        ResourcePermission resourcePermission = new ResourcePermission(idService.nextId(ResourcePermission.class.getName()));
        resourcePermission.setName(Team.class.getName())
                .setPrimKey("")
                .setRoleId(team.getRoleId())
                .setScope(ActionScopeConstant.TENANT)
                .setActionIds(2);
        resourcePermissionLocalProvider.save(resourcePermission);
    }

}

package com.easyray.teamprovider;

import com.easyray.auth.service.impl.SpringSecurityUtil;
import com.easyray.baseapi.constant.ActionScopeConstant;
import com.easyray.baseapi.constant.ResourceActionConstant;
import com.easyray.baseapi.constant.RoleTypeConstant;
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
import com.easyray.teamapi.service.TeamProvider;
import org.apache.dubbo.config.annotation.DubboReference;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;
import java.util.List;

@SpringBootTest()
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ComponentScan(basePackages = {"com.easyray.*"})
class TeamProviderApplicationTests {

    private Logger logger = LoggerFactory.getLogger(TeamProviderApplicationTests.class);
    @Autowired
    @Qualifier("teamLocalProviderImpl")
    private TeamLocalProvider teamLocalProvider;

    @Autowired
    private TeamProvider teamProvider;

    @DubboReference
    private UserLocalProvider userLocalProvider;

    @Autowired
    private ResourcePermissionLocalProvider resourcePermissionLocalProvider;

    @DubboReference
    private IdService idService;

    @DubboReference
    private RoleLocalProvider roleLocalProvider;

    @Autowired
    private PermissionUtil permissionUtil;

    @DubboReference
    private UserRoleLocalProvider userRoleLocalProvider;

    @DubboReference
    private TenantLocalProvider tenantLocalProvider;

    @DubboReference
    private UserTenantRoleLocalProvider userTenantRoleLocalProvider;

    @Autowired
    private SpringSecurityUtil springSecurityUtil;

    @Autowired
    private ResourceActionLocalProvider resourceActionLocalProvider;

    private User initSpringSecurityThreadLocal(String fullName) throws EntityNotExistException {
        User user = userLocalProvider.findByUsername(fullName);

        springSecurityUtil.setUser(user);
        return user;
    }

    @Test
    public void test() {
        User user = springSecurityUtil.getOrSetUser();
        assert user != null;
    }

    @Test
    public void testAdd() {
        Team team = new Team(idService.nextId(Team.class.getName()));
        team.setName(System.currentTimeMillis() + "")
                .setTenantId(3);
        teamLocalProvider.save(team);
    }

    /**
     * 全局角色
     *
     * @throws EntityNotExistException
     */
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
        UserRole userRole = new UserRole();
        userRole.setUserId(user.getId()).setRoleId(role.getId());
        userRoleLocalProvider.save(userRole);

        userRoleLocalProvider.removeById(userRole.getId());
        userLocalProvider.removeById(user.getId());

        //have AA action
        assert permissionUtil.havePermission(user.getId(), 0L, "AA", Team.class.getName());
    }

    /**
     * 租户角色
     *
     * @throws EntityNotExistException
     */
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

    /**
     * 范围角色
     *
     * @throws EntityNotExistException
     */
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

    @Test
    public void testPermission4() throws EntityNotExistException {
        User user = initSpringSecurityThreadLocal("admin");

        //创建角色
        String roleName = "GLOBAL_ROLE" + System.currentTimeMillis();
        Role role = new Role(idService.nextId(Role.class.getName()));
        role.setName(roleName)
                .setType(RoleTypeConstant.GLOBAL_ROLE);
        roleLocalProvider.save(role);
        logger.debug("create role name:{} ,id:{}", roleName, role.getId());
        //配置角色的权限
        ResourceAction resourceAction = resourceActionLocalProvider.fetchByNameAndAction(Team.class.getName(), "VIEW");
        ResourcePermission resourcePermission = new ResourcePermission(idService.nextId(ResourcePermission.class.getName()));
        resourcePermission.setName(Team.class.getName())
                .setScope(ActionScopeConstant.GLOBAL)
                .setRoleId(role.getId())
                .setActionIds(resourceAction.getBitwiseValue());
        resourcePermissionLocalProvider.save(resourcePermission);
        logger.debug("set resource permission id:{}", resourcePermission.getId());
        //创建租户
        Tenant tenant = new Tenant(idService.nextId(Tenant.class.getName()));
        tenant.setName(String.valueOf(System.currentTimeMillis()));
        tenantLocalProvider.save(tenant);
        logger.debug("create tenant id:{} , name:{}", tenant.getId(), tenant.getName());
        //创建team
        Team team1 = new Team(idService.nextId(Team.class.getName()));
        team1.setName("team1")
                .setTenantId(tenant.getId());
        teamLocalProvider.save(team1);
        logger.debug("create team name:{} ,id:{}", team1.getName(), team1.getId());
        Team team2 = new Team(idService.nextId(Team.class.getName()));
        team2.setName("team2")
                .setTenantId(tenant.getId());
        teamLocalProvider.save(team2);
        logger.debug("create team name:{} ,id:{}", team2.getName(), team2.getId());
        Team team3 = new Team(idService.nextId(Team.class.getName()));
        team3.setName("team3")
                .setTenantId(tenant.getId());
        teamLocalProvider.save(team3);
        logger.debug("create team name:{} ,id:{}", team3.getName(), team3.getId());
        //创建用户
        User user1 = new User(idService.nextId(User.class.getName()));
        user1.setUsername(String.valueOf(System.currentTimeMillis()))
                .setPassword("123456");
        userLocalProvider.save(user1);
        logger.debug("create user name:{} ,id:{}", user1.getUsername(), user1.getId());
        //查询
        List<Team> teamList = teamProvider.filterFindBy(null, tenant.getId(), user1.getId());
        assert teamList.size() == 0;

        //添加角色
        UserRole userRole = new UserRole(idService.nextId(UserRole.class.getName()));
        userRole.setUserId(user1.getId())
                .setRoleId(role.getId());
        userRoleLocalProvider.save(userRole);
        logger.debug("create userRole id:{} ", userRole.getId());
        //查询
        teamList = teamProvider.filterFindBy(null, tenant.getId(), user1.getId());
        assert teamList.size() >= 3;

        //清理数据
        roleLocalProvider.removeById(role.getId());
        tenantLocalProvider.removeById(tenant.getId());
        teamLocalProvider.removeByIds(Arrays.asList(team1.getId(), team2.getId(), team3.getId()));
        userLocalProvider.removeById(user1.getId());
    }

    @Test
    public void testPermission5() throws EntityNotExistException {
        User user = initSpringSecurityThreadLocal("admin");

        //创建角色
        String roleName = "TENANT_ROLE" + System.currentTimeMillis();
        Role role = new Role(idService.nextId(Role.class.getName()));
        role.setName(roleName)
                .setType(RoleTypeConstant.GLOBAL_ROLE);
        roleLocalProvider.save(role);
        logger.debug("create role name:{} ,id:{}", roleName, role.getId());
        //配置角色的权限
        ResourceAction resourceAction = resourceActionLocalProvider.fetchByNameAndAction(Team.class.getName(), ResourceActionConstant.VIEW);
        ResourcePermission resourcePermission = new ResourcePermission(idService.nextId(ResourcePermission.class.getName()));
        resourcePermission.setName(Team.class.getName())
                .setScope(ActionScopeConstant.TENANT)
                .setRoleId(role.getId())
                .setActionIds(resourceAction.getBitwiseValue());
        resourcePermissionLocalProvider.save(resourcePermission);
        logger.debug("set resource permission id:{}", resourcePermission.getId());
        //创建租户
        Tenant tenant = new Tenant(idService.nextId(Tenant.class.getName()));
        tenant.setName(String.valueOf(System.currentTimeMillis()));
        tenantLocalProvider.save(tenant);
        logger.debug("create tenant id:{} , name:{}", tenant.getId(), tenant.getName());
        //创建team
        Team team1 = new Team(idService.nextId(Team.class.getName()));
        team1.setName("team1")
                .setTenantId(tenant.getId());
        teamLocalProvider.save(team1);
        logger.debug("create team name:{} ,id:{}", team1.getName(), team1.getId());
        Team team2 = new Team(idService.nextId(Team.class.getName()));
        team2.setName("team2")
                .setTenantId(tenant.getId());
        teamLocalProvider.save(team2);
        logger.debug("create team name:{} ,id:{}", team2.getName(), team2.getId());
        Team team3 = new Team(idService.nextId(Team.class.getName()));
        team3.setName("team3")
                .setTenantId(tenant.getId());
        teamLocalProvider.save(team3);
        logger.debug("create team name:{} ,id:{}", team3.getName(), team3.getId());
        //创建用户
        User user1 = new User(idService.nextId(User.class.getName()));
        user1.setUsername(String.valueOf(System.currentTimeMillis()))
                .setPassword("123456");
        userLocalProvider.save(user1);
        logger.debug("create user name:{} ,id:{}", user1.getUsername(), user1.getId());
        //查询
        List<Team> teamList = teamProvider.filterFindBy(null, tenant.getId(), user1.getId());
        assert teamList.size() == 0;

        //添加角色
        UserTenantRole userTenantRole = new UserTenantRole(idService.nextId(UserTenantRole.class.getName()), user1.getId(), tenant.getId(), role.getId());
        userTenantRoleLocalProvider.save(userTenantRole);
        logger.debug("create userTenantRole id:{} ", userTenantRole.getId());
        //查询
        teamList = teamProvider.filterFindBy(null, tenant.getId(), user1.getId());
        assert teamList.size() >= 3;

        //不可以view别的租户下的team
        Tenant tenant1 = new Tenant(idService.nextId(Tenant.class.getName()));
        tenant1.setName(String.valueOf(System.currentTimeMillis()));
        tenantLocalProvider.save(tenant1);
        logger.debug("create tenant1 id:{} , name:{}", tenant1.getId(), tenant1.getName());

        UserTenantRole userTenantRole1 = new UserTenantRole(idService.nextId(UserTenantRole.class.getName()), user1.getId(), tenant1.getId(), role.getId());
        userTenantRoleLocalProvider.save(userTenantRole1);
        logger.debug("create userTenantRole1 id:{} ", userTenantRole1.getId());
        //查询
        teamList = teamProvider.filterFindBy(null, tenant1.getId(), user1.getId());
        assert teamList.size() == 0;

        //清理数据
        roleLocalProvider.removeById(role.getId());
        tenantLocalProvider.removeById(tenant.getId());
        tenantLocalProvider.removeById(tenant1.getId());
        teamLocalProvider.removeByIds(Arrays.asList(team1.getId(), team2.getId(), team3.getId()));
        userLocalProvider.removeById(user1.getId());
    }

    @Test
    public void testPermission6() throws EntityNotExistException {
        User user = initSpringSecurityThreadLocal("admin");

        //创建角色
        String roleName = "RANGE_ROLE" + System.currentTimeMillis();
        Role role = new Role(idService.nextId(Role.class.getName()));
        role.setName(roleName)
                .setType(RoleTypeConstant.GLOBAL_ROLE);
        roleLocalProvider.save(role);
        logger.debug("create role name:{} ,id:{}", roleName, role.getId());
        //创建租户
        Tenant tenant = new Tenant(idService.nextId(Tenant.class.getName()));
        tenant.setName(String.valueOf(System.currentTimeMillis()));
        tenantLocalProvider.save(tenant);
        logger.debug("create tenant id:{} , name:{}", tenant.getId(), tenant.getName());
        //创建team
        Team team1 = new Team(idService.nextId(Team.class.getName()));
        team1.setName("team1")
                .setTenantId(tenant.getId());
        teamLocalProvider.save(team1);
        logger.debug("create team name:{} ,id:{}", team1.getName(), team1.getId());
        Team team2 = new Team(idService.nextId(Team.class.getName()));
        team2.setName("team2")
                .setTenantId(tenant.getId());
        teamLocalProvider.save(team2);
        logger.debug("create team name:{} ,id:{}", team2.getName(), team2.getId());
        Team team3 = new Team(idService.nextId(Team.class.getName()));
        team3.setName("team3")
                .setTenantId(tenant.getId());
        teamLocalProvider.save(team3);
        logger.debug("create team name:{} ,id:{}", team3.getName(), team3.getId());
        //配置角色的权限
        ResourceAction resourceAction = resourceActionLocalProvider.fetchByNameAndAction(Team.class.getName(), ResourceActionConstant.VIEW);
        ResourcePermission resourcePermission = new ResourcePermission(idService.nextId(ResourcePermission.class.getName()));
        resourcePermission.setName(Team.class.getName())
                .setScope(ActionScopeConstant.ENTITY)
                .setRoleId(role.getId())
                .setPrimKey(String.valueOf(team1.getId()))
                .setActionIds(resourceAction.getBitwiseValue());
        resourcePermissionLocalProvider.save(resourcePermission);
        logger.debug("set resource permission id:{}", resourcePermission.getId());

        //创建用户
        User user1 = new User(idService.nextId(User.class.getName()));
        user1.setUsername(String.valueOf(System.currentTimeMillis()))
                .setPassword("123456");
        userLocalProvider.save(user1);
        logger.debug("create user name:{} ,id:{}", user1.getUsername(), user1.getId());
        //查询
        List<Team> teamList = teamProvider.filterFindBy(null, tenant.getId(), user1.getId());
        assert teamList.size() == 0;

        //添加角色
        UserTenantRole userTenantRole = new UserTenantRole(idService.nextId(UserTenantRole.class.getName()), user1.getId(), tenant.getId(), role.getId());
        userTenantRoleLocalProvider.save(userTenantRole);
        logger.debug("create userTenantRole id:{} ", userTenantRole.getId());
        //查询
        teamList = teamProvider.filterFindBy(null, tenant.getId(), user1.getId());
        assert teamList.size() == 1;

        //清理数据
        roleLocalProvider.removeById(role.getId());
        tenantLocalProvider.removeById(tenant.getId());
        teamLocalProvider.removeByIds(Arrays.asList(team1.getId(), team2.getId(), team3.getId()));
        userLocalProvider.removeById(user1.getId());
    }
}

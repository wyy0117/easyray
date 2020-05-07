package com.easyray.teamprovider;

import com.alibaba.dubbo.config.annotation.Reference;
import com.easyray.auth.service.impl.SpringSecurityThreadLocal;
import com.easyray.auth.service.impl.UserDetailServiceImpl;
import com.easyray.baseapi.constant.ActionScopeConstant;
import com.easyray.common.exception.EntityNotExistException;
import com.easyray.idgeneratorapi.provider.IdService;
import com.easyray.resourcepermission.entity.ResourcePermission;
import com.easyray.resourcepermission.service.ResourcePermissionLocalProvider;
import com.easyray.systemapi.entity.User;
import com.easyray.systemapi.service.UserLocalProvider;
import com.easyray.teamapi.entity.Team;
import com.easyray.teamapi.service.TeamLocalProvider;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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
    public void testAdd() {
        Team team = new Team(idService.nextId(Team.class.getName()));
        team.setName(System.currentTimeMillis() + "")
                .setTenantId(313002);
        teamLocalProvider.save(team);
    }

    @Test
    public void testPermission() {
        User user = springSecurityThreadLocal.getUser();
        List<ResourcePermission> resourcePermissionList = resourcePermissionLocalProvider.findResourcePermission(user.getId(), 313002, "DELETE", Team.class.getName());
        System.out.println("resourcePermissionList.size() = " + resourcePermissionList.size());
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

package com.easyray.systemprovider.provider.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.easyray.idgeneratorapi.service.IdService;
import com.easyray.systemapi.constant.RoleNameConstant;
import com.easyray.systemapi.entity.Group;
import com.easyray.systemapi.entity.Role;
import com.easyray.systemapi.entity.UserGroupRole;
import com.easyray.systemapi.service.GroupLocalProvider;
import com.easyray.systemapi.service.RoleLocalProvider;
import com.easyray.systemapi.service.UserGroupRoleLocalProvider;
import com.easyray.systemprovider.mapper.GroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author wyy
 * @since 2020-02_12
 */
@Service
@Component
public class GroupLocalProviderImpl extends ServiceImpl<GroupMapper, Group> implements GroupLocalProvider {

    @Autowired
    @Qualifier("roleLocalProviderImpl")
    private RoleLocalProvider roleLocalProvider;

    @Autowired
    @Qualifier("userGroupRoleLocalProviderImpl")
    private UserGroupRoleLocalProvider userGroupRoleLocalProvider;

    @Reference
    private IdService idService;

    /**
     * 创建group的时候，肯定会给{@link com.easyray.systemapi.constant.RoleNameConstant#GROUP_OWNER_ROLE_NAME}角色
     *
     * @param entity
     * @return
     */
    @Override
    public boolean save(Group entity) {
        Role role = roleLocalProvider.fetchByName(RoleNameConstant.GROUP_OWNER_ROLE_NAME);
        UserGroupRole userGroupRole = new UserGroupRole(idService.nextId(UserGroupRole.class.getName()), entity.getUserId(), entity.getId(), role.getId());
        userGroupRoleLocalProvider.save(userGroupRole);
        return super.save(entity);
    }
}

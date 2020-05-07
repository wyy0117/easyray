package com.easyray.systemprovider.provider.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.easyray.baseapi.constant.RoleNameConstant;
import com.easyray.baseapi.provider.EasyrayServiceImpl;
import com.easyray.idgeneratorapi.provider.IdService;
import com.easyray.systemapi.entity.Tenant;
import com.easyray.systemapi.entity.Role;
import com.easyray.systemapi.entity.UserTenantRole;
import com.easyray.systemapi.service.TenantLocalProvider;
import com.easyray.systemapi.service.RoleLocalProvider;
import com.easyray.systemapi.service.UserTenantRoleLocalProvider;
import com.easyray.systemprovider.mapper.TenantMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author wyy
 * @since 2020-02_12
 */
@Service
@Component
public class TenantLocalProviderImpl extends EasyrayServiceImpl<TenantMapper, Tenant> implements TenantLocalProvider {

    @Autowired
    @Qualifier("roleLocalProviderImpl")
    private RoleLocalProvider roleLocalProvider;

    @Autowired
    @Qualifier("userTenantRoleLocalProviderImpl")
    private UserTenantRoleLocalProvider userTenantRoleLocalProvider;

    @Reference
    private IdService idService;

    /**
     * 创建tenant的时候，肯定会给{@link RoleNameConstant#TENANT_OWNER_ROLE_NAME}角色
     *
     * @param entity
     * @return
     */
    @Override
    public boolean save(Tenant entity) {
        Role role = roleLocalProvider.fetchByName(RoleNameConstant.TENANT_OWNER_ROLE_NAME);
        UserTenantRole userTenantRole = new UserTenantRole(idService.nextId(UserTenantRole.class.getName()), entity.getUserId(), entity.getId(), role.getId());
        userTenantRoleLocalProvider.save(userTenantRole);
        return super.save(entity);
    }

    @Override
    public Tenant fetchByName(String name) {
        return fetchOneByQueryAndTenantId(new QueryWrapper<Tenant>().lambda().eq(Tenant::getName, name), null);
    }
}

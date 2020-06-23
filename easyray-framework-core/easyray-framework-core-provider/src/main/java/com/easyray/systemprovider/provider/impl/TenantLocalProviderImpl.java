package com.easyray.systemprovider.provider.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.easyray.baseapi.constant.RoleNameConstant;
import com.easyray.baseapi.provider.EasyrayServiceImpl;
import com.easyray.coreapi.entity.Role;
import com.easyray.coreapi.entity.Tenant;
import com.easyray.coreapi.entity.UserTenantRole;
import com.easyray.coreapi.service.RoleLocalProvider;
import com.easyray.coreapi.service.TenantLocalProvider;
import com.easyray.coreapi.service.UserTenantRoleLocalProvider;
import com.easyray.idgeneratorapi.provider.IdService;
import com.easyray.systemprovider.mapper.TenantMapper;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author wyy
 * @since 2020-02_12
 */
@DubboService
@Service
public class TenantLocalProviderImpl extends EasyrayServiceImpl<TenantMapper, Tenant> implements TenantLocalProvider {

    @Autowired
    @Qualifier("roleLocalProviderImpl")
    private RoleLocalProvider roleLocalProvider;

    @Autowired
    @Qualifier("userTenantRoleLocalProviderImpl")
    private UserTenantRoleLocalProvider userTenantRoleLocalProvider;

    @DubboReference
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
        return fetchOneByQuery(new QueryWrapper<Tenant>().lambda().eq(Tenant::getName, name));
    }
}

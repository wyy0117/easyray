package com.easyray.resourcepermission.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.easyray.baseapi.provider.EasyrayServiceImpl;
import com.easyray.resourcepermission.entity.ResourcePermission;
import com.easyray.resourcepermission.mapper.ResourcePermissionMapper;
import com.easyray.resourcepermission.service.ResourcePermissionLocalProvider;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Date: 20-2-2
 * @Author: wyy
 */
@Component
@Transactional
public class ResourcePermissionLocalProviderImpl extends EasyrayServiceImpl<ResourcePermissionMapper, ResourcePermission> implements ResourcePermissionLocalProvider {
    @Override
    public List<ResourcePermission> fetchByName(String name) {
        return list(new QueryWrapper<ResourcePermission>().lambda().eq(ResourcePermission::getName, name));
    }

    @Override
    public ResourcePermission fetchByNameAndRoleId(String entityName, long roleId) {
        return fetchOneByQuery(new QueryWrapper<ResourcePermission>().lambda().eq(ResourcePermission::getName, entityName).eq(ResourcePermission::getRoleId, roleId));
    }

    @Override
    public List<ResourcePermission> findResourcePermission(long userId, long tenantId, String action, String name) {
        return getBaseMapper().findResourcePermission(userId, tenantId, action, name);
    }
}

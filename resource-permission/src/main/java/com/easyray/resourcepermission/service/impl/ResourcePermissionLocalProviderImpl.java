package com.easyray.resourcepermission.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class ResourcePermissionLocalProviderImpl extends ServiceImpl<ResourcePermissionMapper, ResourcePermission> implements ResourcePermissionLocalProvider {
    @Override
    public List<ResourcePermission> fetchByName(String name) {
        return list(new QueryWrapper<ResourcePermission>().eq("name", name));
    }

    @Override
    public ResourcePermission fetchByNameAndRoleId(String entityName, long roleId) {
        return getOne(new QueryWrapper<ResourcePermission>().eq("name", entityName).eq("role_id", roleId));
    }
}

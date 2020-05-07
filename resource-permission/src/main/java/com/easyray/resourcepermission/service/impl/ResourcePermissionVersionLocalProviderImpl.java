package com.easyray.resourcepermission.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.easyray.baseapi.provider.EasyrayServiceImpl;
import com.easyray.resourcepermission.entity.ResourcePermissionVersion;
import com.easyray.resourcepermission.mapper.ResourcePermissionVersionMapper;
import com.easyray.resourcepermission.service.ResourcePermissionVersionLocalProvider;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Date: 20-2-3
 * @Author: wyy
 */
@Component
@Transactional
public class ResourcePermissionVersionLocalProviderImpl extends EasyrayServiceImpl<ResourcePermissionVersionMapper, ResourcePermissionVersion> implements ResourcePermissionVersionLocalProvider {
    @Override
    public ResourcePermissionVersion fetchByModule(String module) {
        return fetchOneByQueryAndTenantId(new QueryWrapper<ResourcePermissionVersion>().lambda().eq(ResourcePermissionVersion::getModule, module), null);
    }
}

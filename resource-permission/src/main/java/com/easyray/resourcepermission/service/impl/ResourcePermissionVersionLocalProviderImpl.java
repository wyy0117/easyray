package com.easyray.resourcepermission.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class ResourcePermissionVersionLocalProviderImpl extends ServiceImpl<ResourcePermissionVersionMapper, ResourcePermissionVersion> implements ResourcePermissionVersionLocalProvider {
    @Override
    public ResourcePermissionVersion fetchByModule(String module) {
        return getOne(new QueryWrapper<ResourcePermissionVersion>().eq("module", module), false);
    }
}

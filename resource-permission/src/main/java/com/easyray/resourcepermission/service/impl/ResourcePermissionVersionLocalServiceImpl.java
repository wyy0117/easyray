package com.easyray.resourcepermission.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.easyray.resourcepermission.entity.ResourcePermissionVersion;
import com.easyray.resourcepermission.mapper.ResourcePermissionVersionMapper;
import com.easyray.resourcepermission.service.ResourcePermissionVersionLocalService;
import org.springframework.stereotype.Component;

/**
 * @Date: 20-2-3
 * @Author: wyy
 */
@Component
public class ResourcePermissionVersionLocalServiceImpl extends ServiceImpl<ResourcePermissionVersionMapper, ResourcePermissionVersion> implements ResourcePermissionVersionLocalService {
    @Override
    public ResourcePermissionVersion fetchByModule(String module) {
        return getOne(new QueryWrapper<ResourcePermissionVersion>().eq("module", module), false);
    }
}

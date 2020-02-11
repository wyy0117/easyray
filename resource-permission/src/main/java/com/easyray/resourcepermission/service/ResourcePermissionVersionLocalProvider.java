package com.easyray.resourcepermission.service;

import com.easyray.baseapi.provider.BaseLocalProvider;
import com.easyray.resourcepermission.entity.ResourcePermissionVersion;

/**
 * @Date: 20-2-3
 * @Author: wyy
 */
public interface ResourcePermissionVersionLocalProvider extends BaseLocalProvider<ResourcePermissionVersion> {
    public ResourcePermissionVersion fetchByModule(String module);
}

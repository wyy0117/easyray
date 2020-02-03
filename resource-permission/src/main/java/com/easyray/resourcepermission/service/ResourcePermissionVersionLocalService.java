package com.easyray.resourcepermission.service;

import com.easyray.baseapi.service.BaseLocalService;
import com.easyray.resourcepermission.entity.ResourcePermissionVersion;

/**
 * @Date: 20-2-3
 * @Author: wyy
 */
public interface ResourcePermissionVersionLocalService extends BaseLocalService<ResourcePermissionVersion> {
    public ResourcePermissionVersion fetchByModule(String module);
}

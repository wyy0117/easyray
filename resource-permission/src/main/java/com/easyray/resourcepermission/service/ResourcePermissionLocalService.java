package com.easyray.resourcepermission.service;

import com.easyray.baseapi.service.BaseLocalService;
import com.easyray.resourcepermission.entity.ResourcePermission;

import java.util.List;

/**
 * @Date: 20-2-2
 * @Author: wyy
 */
public interface ResourcePermissionLocalService extends BaseLocalService<ResourcePermission> {
    public List<ResourcePermission> fetchByEntityName(String entityName);
}

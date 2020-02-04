package com.easyray.resourcepermission.service;

import com.easyray.baseapi.service.BaseLocalService;
import com.easyray.resourcepermission.entity.ResourceAction;

import java.util.List;

/**
 * @Date: 20-2-2
 * @Author: wyy
 */
public interface ResourceActionLocalService extends BaseLocalService<ResourceAction> {
    public List<ResourceAction> fetchByEntityName(String entityName);
}

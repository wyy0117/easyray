package com.easyray.resourcepermission.service;

import com.easyray.baseapi.service.BaseLocalService;
import com.easyray.resourcepermission.entity.ResourceActionVersion;

/**
 * @Date: 20-2-3
 * @Author: wyy
 */
public interface ResourceActionVersionLocalService extends BaseLocalService<ResourceActionVersion> {
    public ResourceActionVersion fetchByModule(String module);
}

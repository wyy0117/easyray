package com.easyray.resourcepermission.service;

import com.easyray.baseapi.provider.BaseLocalProvider;
import com.easyray.resourcepermission.entity.ResourceActionVersion;

/**
 * @Date: 20-2-3
 * @Author: wyy
 */
public interface ResourceActionVersionLocalProvider extends BaseLocalProvider<ResourceActionVersion> {
    public ResourceActionVersion fetchByModule(String module);
}

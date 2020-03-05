package com.easyray.resourcepermission.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.easyray.baseapi.provider.EasyrayServiceImpl;
import com.easyray.resourcepermission.entity.ResourceActionVersion;
import com.easyray.resourcepermission.mapper.ResourceActionVersionMapper;
import com.easyray.resourcepermission.service.ResourceActionVersionLocalProvider;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Date: 20-2-3
 * @Author: wyy
 */
@Component
@Transactional
public class ResourceActionVersionLocalProviderImpl extends EasyrayServiceImpl<ResourceActionVersionMapper, ResourceActionVersion> implements ResourceActionVersionLocalProvider {
    @Override
    public ResourceActionVersion fetchByModule(String module) {
        return fetchOneByQueryAndGroupId(new QueryWrapper<ResourceActionVersion>().eq("module", module), null);
    }
}

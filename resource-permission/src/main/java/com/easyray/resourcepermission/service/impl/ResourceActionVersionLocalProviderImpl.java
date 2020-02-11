package com.easyray.resourcepermission.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class ResourceActionVersionLocalProviderImpl extends ServiceImpl<ResourceActionVersionMapper, ResourceActionVersion> implements ResourceActionVersionLocalProvider {
    @Override
    public ResourceActionVersion fetchByModule(String module) {
        return getOne(new QueryWrapper<ResourceActionVersion>().eq("module", module), false);
    }
}

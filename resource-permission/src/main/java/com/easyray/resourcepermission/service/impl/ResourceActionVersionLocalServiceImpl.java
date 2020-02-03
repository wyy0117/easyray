package com.easyray.resourcepermission.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.easyray.resourcepermission.entity.ResourceActionVersion;
import com.easyray.resourcepermission.mapper.ResourceActionVersionMapper;
import com.easyray.resourcepermission.service.ResourceActionVersionLocalService;
import org.springframework.stereotype.Component;

/**
 * @Date: 20-2-3
 * @Author: wyy
 */
@Component
public class ResourceActionVersionLocalServiceImpl extends ServiceImpl<ResourceActionVersionMapper, ResourceActionVersion> implements ResourceActionVersionLocalService {
    @Override
    public ResourceActionVersion fetchByModule(String module) {
        return getOne(new QueryWrapper<ResourceActionVersion>().eq("module", module), false);
    }
}

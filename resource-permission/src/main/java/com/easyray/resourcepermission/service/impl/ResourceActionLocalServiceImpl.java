package com.easyray.resourcepermission.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.easyray.resourcepermission.entity.ResourceAction;
import com.easyray.resourcepermission.mapper.ResourceActionMapper;
import com.easyray.resourcepermission.service.ResourceActionLocalService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Date: 20-2-2
 * @Author: wyy
 */
@Component
public class ResourceActionLocalServiceImpl extends ServiceImpl<ResourceActionMapper, ResourceAction> implements ResourceActionLocalService {

    @Override
    public List<ResourceAction> fetchByEntityName(String entityName) {
        return list(new QueryWrapper<ResourceAction>().eq("entity_name", entityName));
    }
}

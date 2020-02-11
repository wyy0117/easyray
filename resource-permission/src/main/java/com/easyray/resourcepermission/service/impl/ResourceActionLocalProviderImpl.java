package com.easyray.resourcepermission.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.easyray.resourcepermission.entity.ResourceAction;
import com.easyray.resourcepermission.mapper.ResourceActionMapper;
import com.easyray.resourcepermission.service.ResourceActionLocalProvider;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Date: 20-2-2
 * @Author: wyy
 */
@Component
@Transactional
public class ResourceActionLocalProviderImpl extends ServiceImpl<ResourceActionMapper, ResourceAction> implements ResourceActionLocalProvider {

    @Override
    public List<ResourceAction> fetchByName(String name) {
        return list(new QueryWrapper<ResourceAction>().eq("name", name));
    }

    @Override
    public void deleteByNameAndActions(String name, List<String> actionList) {

        remove(new QueryWrapper<ResourceAction>().eq("name", name).in("action", actionList));
    }

    @Override
    public int countByName(String name) {
        return count(new QueryWrapper<ResourceAction>().eq("name", name));
    }

    @Override
    public List<ResourceAction> fetchByNameAndActions(String name, List<String> actionList) {
        return list(new QueryWrapper<ResourceAction>().eq("name", name).in("action", actionList));
    }
}

package com.easyray.roleservice.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.easyray.roleapi.entity.Role;
import com.easyray.roleapi.service.RoleLocalService;
import com.easyray.roleservice.mapper.RoleMapper;
import org.springframework.stereotype.Component;

/**
 * @author wyy
 * @since 2020-02_08
 */
@Service
@Component("roleLocalService")
public class RoleLocalServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleLocalService {


    @Override
    public Role fetchByName(String name) {
        return getOne(new QueryWrapper<Role>().eq("name", name));
    }
}

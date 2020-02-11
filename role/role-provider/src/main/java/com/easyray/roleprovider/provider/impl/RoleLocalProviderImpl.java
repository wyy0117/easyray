package com.easyray.roleprovider.provider.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.easyray.roleapi.entity.Role;
import com.easyray.roleapi.provider.RoleLocalProvider;
import com.easyray.roleprovider.mapper.RoleMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wyy
 * @since 2020-02_08
 */
@Service
@Component
@Transactional
public class RoleLocalProviderImpl extends ServiceImpl<RoleMapper, Role> implements RoleLocalProvider {


    @Override
    public Role fetchByName(String name) {
        return getOne(new QueryWrapper<Role>().eq("name", name));
    }
}

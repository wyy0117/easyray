package com.easyray.systemprovider.provider.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.easyray.common.exception.EntityNotExistException;
import com.easyray.common.exception.filter.CustomThrowable;
import com.easyray.idgeneratorapi.service.IdService;
import com.easyray.systemapi.entity.Role;
import com.easyray.systemapi.entity.User;
import com.easyray.systemapi.service.RoleLocalProvider;
import com.easyray.systemprovider.mapper.RoleMapper;
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

    @Reference
    private IdService idService;

    @Override
    public Role fetchByName(String name) {
        return getOne(new QueryWrapper<Role>().eq("name", name));
    }

    @Override
    public Role findByName(String name) throws EntityNotExistException {
        Role role = fetchByName(name);
        if (role == null) {
            throw new EntityNotExistException(new CustomThrowable(Role.class, "name: " + name));
        }
        return role;
    }


}

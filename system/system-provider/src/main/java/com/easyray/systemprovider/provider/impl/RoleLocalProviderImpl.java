package com.easyray.systemprovider.provider.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.easyray.baseapi.provider.EasyrayServiceImpl;
import com.easyray.common.exception.EntityNotExistException;
import com.easyray.idgeneratorapi.provider.IdService;
import com.easyray.systemapi.entity.Role;
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
public class RoleLocalProviderImpl extends EasyrayServiceImpl<RoleMapper, Role> implements RoleLocalProvider {

    @Reference
    private IdService idService;

    @Override
    public Role fetchByName(String name) {
        return fetchOneByQueryAndGroupId(new QueryWrapper<Role>().lambda().eq(Role::getName, name), null);
    }

    @Override
    public Role findByName(String name) throws EntityNotExistException {

        return findOneByQueryAndGroupId(new QueryWrapper<Role>().lambda().eq(Role::getName, name), null);
    }


}

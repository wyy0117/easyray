package com.easyray.systemprovider.provider.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.easyray.baseapi.provider.EasyrayServiceImpl;
import com.easyray.common.exception.EntityNotExistException;
import com.easyray.coreapi.entity.Role;
import com.easyray.coreapi.service.RoleLocalProvider;
import com.easyray.idgeneratorapi.provider.IdService;
import com.easyray.systemprovider.mapper.RoleMapper;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wyy
 * @since 2020-02_08
 */
@DubboService
@Service
@Transactional
public class RoleLocalProviderImpl extends EasyrayServiceImpl<RoleMapper, Role> implements RoleLocalProvider {

    @DubboReference
    private IdService idService;

    @Override
    public Role fetchByName(String name) {
        return fetchOneByQuery(new QueryWrapper<Role>().lambda().eq(Role::getName, name));
    }

    @Override
    public Role findByName(String name) throws EntityNotExistException {

        return findOneByQuery(new QueryWrapper<Role>().lambda().eq(Role::getName, name));
    }


}

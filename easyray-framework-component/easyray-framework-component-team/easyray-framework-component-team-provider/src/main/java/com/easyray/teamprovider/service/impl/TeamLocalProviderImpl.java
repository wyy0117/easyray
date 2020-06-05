package com.easyray.teamprovider.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.easyray.baseapi.constant.RoleTypeConstant;
import com.easyray.baseapi.provider.EasyrayServiceImpl;
import com.easyray.idgeneratorapi.provider.IdService;
import com.easyray.coreapi.entity.Role;
import com.easyray.coreapi.service.RoleLocalProvider;
import com.easyray.teamapi.entity.Team;
import com.easyray.teamapi.service.TeamLocalProvider;
import com.easyray.teamprovider.mapper.TeamMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wyy
 * @since 2020-02_11
 */
@Service
@Component
@Transactional
public class TeamLocalProviderImpl extends EasyrayServiceImpl<TeamMapper, Team> implements TeamLocalProvider {

    @Reference
    private RoleLocalProvider roleLocalProvider;

    @Reference
    private IdService idService;

    /**
     * 添加team的同时添加team对应的角色
     *
     * @param entity
     * @return
     */
    @Override
    public boolean save(Team entity) {
        Long id = entity.getId();
        Role role = new Role(idService.nextId(Role.class.getName()));
        role.setType(RoleTypeConstant.RANGE_ROLE)
                .setName(id.toString());
        roleLocalProvider.save(role);

        entity.setRoleId(role.getId());

        return super.save(entity);
    }
}

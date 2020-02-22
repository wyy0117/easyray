package com.easyray.systemprovider.provider.impl;

import com.easyray.baseapi.provider.EasyrayServiceImpl;
import com.easyray.systemapi.entity.Group;
import com.easyray.systemapi.service.GroupCheckPermission;
import com.easyray.systemprovider.mapper.GroupMapper;
import org.springframework.stereotype.Component;

/**
 * @Date: 2020-02_12
 * @Author: wyy
 */
@Component
public class GroupCheckPermissionImpl extends EasyrayServiceImpl<GroupMapper, Group> implements GroupCheckPermission {

    @Override
    public Group fetchByName(String name) {
        return null;
    }
}

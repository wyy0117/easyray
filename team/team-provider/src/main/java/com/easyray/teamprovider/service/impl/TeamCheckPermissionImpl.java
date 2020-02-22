package com.easyray.teamprovider.service.impl;

import com.easyray.baseapi.provider.EasyrayServiceImpl;
import com.easyray.teamapi.entity.Team;
import com.easyray.teamapi.service.TeamCheckPermission;
import com.easyray.teamprovider.mapper.TeamMapper;
import org.springframework.stereotype.Component;

/**
 * @Date: 2020-02_11
 * @Author: wyy
 */
@Component
public class TeamCheckPermissionImpl extends EasyrayServiceImpl<TeamMapper, Team> implements TeamCheckPermission {

}

package com.easyray.teamprovider.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.easyray.teamapi.entity.Team;
import com.easyray.teamapi.service.TeamCheckPermission;
import com.easyray.teamprovider.mapper.TeamMapper;
import org.springframework.stereotype.Component;

/**
 * @Date: 2020-02_11
 * @Author: wyy
 */
@Component
public class TeamCheckPermissionImpl extends ServiceImpl<TeamMapper, Team> implements TeamCheckPermission {

}

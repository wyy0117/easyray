package com.easyray.teamprovider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.easyray.baseapi.provider.EasyrayServiceImpl;
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


}

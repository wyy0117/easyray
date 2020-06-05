package com.easyray.teamprovider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.easyray.teamapi.service.TeamProvider;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Date: 2020-02_11
 * @Author: wyy
 */
@Service
@Component
@Transactional
public class TeamProviderImpl extends TeamLocalProviderImpl implements TeamProvider {

}

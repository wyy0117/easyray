package com.easyray.teamprovider.service.impl;

import com.easyray.teamapi.service.TeamProvider;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Date: 2020-02_11
 * @Author: wyy
 */
@DubboService
@Service
@Transactional
public class TeamProviderImpl extends TeamLocalProviderImpl implements TeamProvider {

}

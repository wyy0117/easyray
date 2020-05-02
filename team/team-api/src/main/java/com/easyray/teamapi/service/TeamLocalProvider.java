package com.easyray.teamapi.service;

import com.easyray.baseapi.provider.BaseLocalProvider;
import com.easyray.teamapi.entity.Team;

/**
 * @Date: 2020-02_11
 * @Author: wyy
 */
public interface TeamLocalProvider extends BaseLocalProvider<Team> {


    @Override
    boolean save(Team entity);
}

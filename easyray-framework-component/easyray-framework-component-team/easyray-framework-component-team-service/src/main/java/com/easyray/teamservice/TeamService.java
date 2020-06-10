package com.easyray.teamservice;

import com.alibaba.dubbo.config.annotation.Reference;
import com.easyray.teamapi.entity.Team;
import com.easyray.teamapi.service.TeamLocalProvider;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Date: 2020/6/10
 * @Author: wyy
 */
@Service
public class TeamService {

    @Reference
    private TeamLocalProvider teamLocalProvider;

    public List<Team> teamList() {
        return teamLocalProvider.list();
    }

    public void test(){
        teamLocalProvider.test();
    }
}

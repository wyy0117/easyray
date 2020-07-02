package com.easyray.teamservice;

import com.easyray.common.exception.EasyrayAbstractException;
import com.easyray.idgeneratorapi.provider.IdService;
import com.easyray.teamapi.entity.Team;
import com.easyray.teamapi.service.TeamLocalProvider;
import io.seata.spring.annotation.GlobalTransactional;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Date: 2020/7/2
 * @Author: wyy
 */
@Service
public class TestService {
    @DubboReference
    private IdService idService;
    @DubboReference
    private TeamLocalProvider teamLocalProvider;

    @GlobalTransactional
    public void test() throws EasyrayAbstractException {
//        teamLocalProvider.test();
        Team team = new Team(idService.nextId(Team.class.getName()));
        team.setName("22");
        team.setCreateDate(new Date());
        teamLocalProvider.add(team);
//        if (true) {
//            throw new RuntimeException("事务撤回，逻辑回滚");
//        }

    }
}

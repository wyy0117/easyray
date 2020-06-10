package com.easyray.teamweb.controller;

import com.easyray.auth.service.impl.SpringSecurityUtil;
import com.easyray.teamapi.entity.Team;
import com.easyray.teamservice.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Date: 2020/6/10
 * @Author: wyy
 */
@RestController
public class TeamController {

    @Autowired
    private TeamService teamService;
    @Autowired
    private SpringSecurityUtil springSecurityUtil;

    /**
     * curl -v -H 'X-Auth-Token: 44249470-c254-49f0-ab98-6b4c49099f77' 'localhost:7003/team/teams'
     * @return
     */
    @GetMapping("teams")
    public List<Team> teams(){
        SecurityContextHolder.getContext().getAuthentication();
         teamService.test();
         return null;
    }

}

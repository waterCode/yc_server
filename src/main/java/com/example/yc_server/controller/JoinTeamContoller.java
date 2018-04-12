package com.example.yc_server.controller;

import com.example.yc_server.domain.RegistrationForm;
import com.example.yc_server.repository.CompetitionFromRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/team")
public class JoinTeamContoller {

    @Autowired
    CompetitionFromRepository competitionFromReprosity;

    @PostMapping(value = "/joinCompetition")
    public void joinCompetition(@RequestBody RegistrationForm form){
        if (form !=null){
            competitionFromReprosity.save(form);//保存到数据库
        }
    }


}

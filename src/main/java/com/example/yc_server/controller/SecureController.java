package com.example.yc_server.controller;


import com.example.yc_server.domain.GradeTeam;
import com.example.yc_server.domain.RegistrationForm;
import com.example.yc_server.domain.SysUser;
import com.example.yc_server.repository.CompetitionFromRepository;
import com.example.yc_server.repository.RegisterRepository;
import com.example.yc_server.repository.TeamGradeRepository;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/secure")
public class SecureController {


    @Autowired
    private RegisterRepository registerRepository;

    @Autowired
    private CompetitionFromRepository competitionFromRepository;

    @Autowired
    private TeamGradeRepository teamGradeRepository;


    @GetMapping("/users")
    public  List<SysUser> getUser(HttpServletRequest request){
        //只有管理者才有权限
        List<SysUser> all=null;
        Claims claims = (Claims) request.getAttribute("claims");
        String userName = claims.getSubject();
        if(userName.equals("admin")){
            //是管理员，则返回成员列表
            all = registerRepository.findAll();
            Iterator<SysUser> iterator = all.iterator();
            while (iterator.hasNext()){
                SysUser next = iterator.next();
                if(next.getUserName().equals("admin")){
                    iterator.remove();
                }
            }

        }
        return all;
    }

    @GetMapping("/participants")
    public  List<RegistrationForm> getParticipants(HttpServletRequest request){
        //只有管理者才有权限
        List<RegistrationForm> all=null;
        Claims claims = (Claims) request.getAttribute("claims");
        String userName = claims.getSubject();
        if(userName.equals("admin")){
            //是管理员，则返回成员列表
            all = competitionFromRepository.findAll();

        }
        return all;
    }


    @PostMapping("/submitGrade")
    public void saveTeamGrade(@RequestBody GradeTeam gradeTeam,HttpServletRequest request){
        Claims claims = (Claims) request.getAttribute("claims");
        String userName = claims.getSubject();
        gradeTeam.setScorerName(userName);
        //如果还没评分则保存

    }







    @PostMapping("/joinUs")
    public boolean JoinUs(){
        return true;
    }


}

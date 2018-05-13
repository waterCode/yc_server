package com.example.yc_server.controller;


import com.example.yc_server.domain.SysUser;
import com.example.yc_server.repository.RegisterRepository;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/secure")
public class SecureController {

    @Autowired
    private RegisterRepository registerRepository;


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

    @PostMapping("/joinUs")
    public boolean JoinUs(){
        return true;
    }

    @PostMapping("/joinCompetition")
    public boolean JoinCompetition(){
        return true;
    }
}

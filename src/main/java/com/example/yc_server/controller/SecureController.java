package com.example.yc_server.controller;


import com.example.yc_server.domain.SysUser;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/secure")
public class SecureController {




    @GetMapping("/users")
    public String getUser(HttpServletRequest request){
        //只有管理者才有权限
        Claims claims = (Claims) request.getAttribute("claims");
        String userName = claims.getSubject();

        return userName;
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

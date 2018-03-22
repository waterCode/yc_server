package com.example.yc_server.controller;


import com.example.yc_server.domain.Result;
import com.example.yc_server.domain.User;
import com.example.yc_server.repository.RegisterRepository;
import com.example.yc_server.service.LoginAndRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RegisterController {

    @Autowired
    private LoginAndRegisterService loginAndRegisterService;


    @PostMapping(value = "/register" )
    public Result addUser(@RequestBody User newUser){
        Result result = new Result();
        User user = loginAndRegisterService.addUser(newUser);
        if(user == null){
            //表示注册失败
            result.setResult(false);
            result.setMessage("用户名已被注册");
        }else {
            result.setResult(true);
            result.setMessage("success");
        }
        return result;
    }
}

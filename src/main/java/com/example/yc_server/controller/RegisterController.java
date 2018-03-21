package com.example.yc_server.controller;


import com.example.yc_server.domain.Result;
import com.example.yc_server.domain.User;
import com.example.yc_server.repository.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RegisterController {

    @Autowired
    private RegisterRepository registerRepository;


    public boolean isUserExit(String userName){
        List<User> userList = registerRepository.findAll();
        for (User user :userList){
            if(user.getName().equals(userName)){
                return true;
            }
        }
        return false;//默认是false
    }
    @PostMapping(value = "/register" )
    public Result addUser(@RequestBody User newUser){
        Result result = new Result();
        if(isUserExit(newUser.getName())){
            return result;
        }else {
            //添加用户
        }
    }
}

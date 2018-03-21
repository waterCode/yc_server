package com.example.yc_server.controller;


import com.example.yc_server.domain.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @RequestMapping(value="/login" ,method = RequestMethod.POST)
    public User login(@RequestBody User user){
        User test = new User("zmc","123");
        return user;
    }

}

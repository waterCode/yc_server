package com.example.yc_server.controller;


import com.example.yc_server.domain.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @RequestMapping(value="/login" )
    public User login(@RequestBody User user){

        return user;
    }

}

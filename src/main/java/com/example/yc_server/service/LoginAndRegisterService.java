package com.example.yc_server.service;


import com.example.yc_server.domain.User;
import com.example.yc_server.repository.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginAndRegisterService {

    @Autowired
    private RegisterRepository registerRepository;



    public User addUser(@NonNull User newUser){
        List<User> userList = registerRepository.findByName(newUser.getName());
        if(userList == null|| userList.size()==0){
            //可以注册
            return registerRepository.save(newUser);
        }else {
            return null;
        }
    }

}

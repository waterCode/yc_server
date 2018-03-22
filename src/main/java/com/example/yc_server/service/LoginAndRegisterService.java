package com.example.yc_server.service;


import com.example.yc_server.domain.Result;
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


    public User addUser(@NonNull User newUser) {
        List<User> userList = registerRepository.findByName(newUser.getName());
        if (userList == null || userList.size() == 0) {
            //可以注册
            return registerRepository.save(newUser);
        } else {
            return null;
        }
    }


    public Result login(@NonNull User user) {
        Result result = new Result();
        List<User> userList = registerRepository.findByName(user.getName());
        if (userList.size() > 0) {
            //表示找到对应的用户
            if (userList.get(0).getPwd().equals(user.getPwd())) {
                result.setResult(true);
                result.setMessage("登录成功");
            } else {
                result.setResult(false);
                result.setMessage("密码错误");
            }
        } else {
            result.setResult(false);
            result.setMessage("用户不存在");
        }
        return result;
    }
}

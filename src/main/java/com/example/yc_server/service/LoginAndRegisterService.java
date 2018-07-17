package com.example.yc_server.service;



import com.example.yc_server.domain.LoginResult;
import com.example.yc_server.domain.Result;
import com.example.yc_server.domain.SysUser;
import com.example.yc_server.repository.RegisterRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class LoginAndRegisterService  {

    @Autowired
    private RegisterRepository registerRepository;


    public SysUser addUser(@NonNull SysUser newUser) {
        SysUser userDataBases = registerRepository.findByUserName(newUser.getUserName());

        if (userDataBases !=null) {
            //已经存在注册
            return null;
        } else {
            return registerRepository.save(newUser);
        }
    }


    public Result login(@NonNull SysUser user) {
        LoginResult result = new LoginResult();
        String jwtToken = "";
        SysUser userDataBases = registerRepository.findByUserName(user.getUserName());
        if (userDataBases != null) {
            //表示找到对应的用户
            if (userDataBases.getPassword().equals(user.getPassword())) {
                result.setResult(true);
                result.setMessage("登录成功");
                result.setUserName(user.getUserName());
                result.setRole(userDataBases.getRoles());
                //返回token
                jwtToken = Jwts.builder().setSubject(userDataBases.getRoles()).claim("roles", "user").setIssuedAt(new Date())
                        .signWith(SignatureAlgorithm.HS256, "secretkey").compact();
                result.setToken(jwtToken);
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

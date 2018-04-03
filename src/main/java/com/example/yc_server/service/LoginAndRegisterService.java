package com.example.yc_server.service;



import com.example.yc_server.domain.Result;
import com.example.yc_server.domain.SysUser;
import com.example.yc_server.repository.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;


@Service
public class LoginAndRegisterService  {

    @Autowired
    private RegisterRepository registerRepository;


    public SysUser addUser(@NonNull SysUser newUser) {
        SysUser userDataBases = registerRepository.findByUsername(newUser.getUsername());

        if (userDataBases !=null) {
            //可以注册
            return registerRepository.save(newUser);
        } else {
            return null;
        }
    }


    public Result login(@NonNull SysUser user) {
        Result result = new Result();
        SysUser userDataBases = registerRepository.findByUsername(user.getUsername());
        if (userDataBases != null) {
            //表示找到对应的用户
            if (userDataBases.getPassword().equals(user.getPassword())) {
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

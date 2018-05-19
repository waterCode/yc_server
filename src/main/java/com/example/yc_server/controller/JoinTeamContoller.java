package com.example.yc_server.controller;

import com.example.yc_server.domain.BaseResult;
import com.example.yc_server.domain.JoinUsForm;
import com.example.yc_server.domain.RegistrationForm;
import com.example.yc_server.repository.CompetitionFromRepository;
import com.example.yc_server.repository.JoinUsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
@RequestMapping("/team")
public class JoinTeamContoller {

    @Autowired
    CompetitionFromRepository competitionFromReprosity;

    @Autowired
    JoinUsRepository joinUsRepository;

    @PostMapping(value = "/joinCompetition")
    public BaseResult joinCompetition(@RequestBody RegistrationForm form){
        BaseResult result = new BaseResult();
        if (form !=null){
            competitionFromReprosity.save(form);//保存到数据库
            result.setResult(true);
        }else {
            result.setResult(false);
        }
        return result;
    }

    @PostMapping(value = "/joinCompetitionAll")
    public BaseResult joinCompetitionAll(@RequestParam("uploadfile") MultipartFile file){
        BaseResult result = new BaseResult();
        if (!file.isEmpty()) {
            try {
                // 这里只是简单例子，文件直接输出到项目路径下。
                // 实际项目中，文件需要输出到指定位置，需要在增加代码处理。
                // 还有关于文件格式限制、文件大小限制，详见：中配置。
                //
                BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream(new File(file.getOriginalFilename())));
                out.write(file.getBytes());
                out.flush();
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                result.setResult(false);
                return result;
            } catch (IOException e) {
                e.printStackTrace();
                result.setResult(false);
                return result;
            }
            result.setResult(true);
            return result;
        } else {
            result.setResult(false);
            return result;
        }
    }

    @PostMapping(value = "/joinUs")
    public BaseResult joinUs(@RequestBody JoinUsForm form){
        BaseResult result = new BaseResult();
        if (form !=null){
            joinUsRepository.save(form);//报名表保存到数据库
            result.setResult(true);
        }else {
            result.setResult(false);
        }
        return result;
    }


}

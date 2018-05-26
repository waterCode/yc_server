package com.example.yc_server.controller;

import com.example.yc_server.domain.BaseResult;
import com.example.yc_server.domain.JoinUsForm;
import com.example.yc_server.domain.RegistrationForm;
import com.example.yc_server.repository.CompetitionFromRepository;
import com.example.yc_server.repository.JoinUsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.List;

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
    public BaseResult joinCompetitionAll(HttpServletRequest request) {
        MultipartHttpServletRequest params = ((MultipartHttpServletRequest) request);
        List<MultipartFile> files = ((MultipartHttpServletRequest) request)
                .getFiles("uploadfile");
        saveStringData(params);
        String captionName = params.getParameter("captionName");
        String weChat = params.getParameter("weChat");//用队伍名+微信号作为文件夹名字
        String path = "C:/YC/"+captionName+weChat;
        File dir = new File(path);
        if(!dir.exists()){
            dir.mkdirs();
        }
        BaseResult result = new BaseResult();
        if (files != null) {
            for (MultipartFile file : files) {
                if (!file.isEmpty()) {
                    try {
                        // 这里只是简单例子，文件直接输出到项目路径下。
                        // 实际项目中，文件需要输出到指定位置，需要在增加代码处理。
                        // 还有关于文件格式限制、文件大小限制，详见：中配置。
                        //
                        BufferedOutputStream out = new BufferedOutputStream(
                                new FileOutputStream(new File(path+"/"+file.getOriginalFilename())));
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
                } else {
                    result.setResult(false);
                    return result;
                }
            }
        } else {
            result.setResult(false);
            return result;
        }
        result.setResult(true);
        return result;


    }

    private void saveStringData(MultipartHttpServletRequest params) {
        String captionName = params.getParameter("captionName");
        String zhuanYe = params.getParameter("zhuanYe");
        String xueHao = params.getParameter("xueHao");
        String telephone = params.getParameter("telephone");
        String weChat = params.getParameter("weChat");
        String school = params.getParameter("school");
        String duiWuName = params.getParameter("duiWuName");
        String zuoPinName = params.getParameter("zuoPinName");
        String aboutTest = params.getParameter("aboutTest");
        String aboutFunction = params.getParameter("aboutFunction");
        String aboutNews = params.getParameter("aboutNews");
        String technologyWay = params.getParameter("technologyWay");
        String technologyCase = params.getParameter("technologyCase");
        String productIntroduce = params.getParameter("productIntroduce");
        String adress = params.getParameter("adress");
        String teamMateOneName = params.getParameter("teamMateOneName");
        String teamMateOneClass = params.getParameter("teamMateOneClass");
        String teamMateOneTelephone = params.getParameter("teamMateOneTelephone");
        String teamMateTwoName = params.getParameter("teamMateTwoName");
        String teamMateTwoClass = params.getParameter("teamMateTwoClass");
        String teamMateTwoTelephone = params.getParameter("teamMateTwoTelephone");
        String teamMateThreeName = params.getParameter("teamMateThreeName");
        String teamMateThreeClass = params.getParameter("teamMateThreeClass");
        String teamMateThreeTelephone = params.getParameter("teamMateThreeTelephone");
        String teamMateFourName = params.getParameter("teamMateFourName");
        String teamMateFourClass = params.getParameter("teamMateFourClass");
        String teamMateFourTelephone = params.getParameter("teamMateFourTelephone");
        RegistrationForm registrationForm = new RegistrationForm();
        registrationForm.setTeamMateOneName(teamMateOneName);
        registrationForm.setTeamMateOneClass(teamMateOneClass);
        registrationForm.setTeamMateOneTelephone(teamMateOneTelephone);

        registrationForm.setTeamMateTwoName(teamMateTwoName);
        registrationForm.setTeamMateTwoClass(teamMateTwoClass);
        registrationForm.setTeamMateTwoTelephone(teamMateTwoTelephone);

        registrationForm.setTeamMateThreeName(teamMateThreeName);
        registrationForm.setTeamMateThreeClass(teamMateThreeClass);
        registrationForm.setTeamMateThreeTelephone(teamMateThreeTelephone);

        registrationForm.setTeamMateFourName(teamMateFourName);
        registrationForm.setTeamMateFourClass(teamMateFourClass);
        registrationForm.setTeamMateFourTelephone(teamMateFourTelephone);

        registrationForm.setCaptionName(captionName);
        registrationForm.setZhuanYe(zhuanYe);
        registrationForm.setXueHao(xueHao);
        registrationForm.setTelephone(telephone);
        registrationForm.setWeChat(weChat);
        registrationForm.setSchool(school);
        registrationForm.setDuiWuName(duiWuName);
        registrationForm.setZuoPinName(zuoPinName);
        registrationForm.setAboutTest(aboutTest);
        registrationForm.setAboutFunction(aboutFunction);
        registrationForm.setAboutNews(aboutNews);
        registrationForm.setTechnologyWay(technologyWay);
        registrationForm.setTechnologyCase(technologyCase);
        registrationForm.setProductIntroduce(productIntroduce);
        registrationForm.setAdress(adress);
        competitionFromReprosity.save(registrationForm);//保存
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

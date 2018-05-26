package com.example.yc_server.controller;


import com.example.yc_server.domain.*;
import com.example.yc_server.repository.CompetitionFromRepository;
import com.example.yc_server.repository.RegisterRepository;
import com.example.yc_server.repository.TeamGradeRepository;
import com.example.yc_server.service.EmailService;
import io.jsonwebtoken.Claims;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/secure")
public class SecureController {


    @Autowired
    private RegisterRepository registerRepository;

    @Autowired
    private CompetitionFromRepository competitionFromRepository;

    @Autowired
    private TeamGradeRepository teamGradeRepository;

    @Autowired
    private EmailService emailService;


    @GetMapping("/getRegistrationForm")
    public BaseResult getRegistrationForm (@RequestParam(value = "id", required = true)Long id,HttpServletRequest request){
        RegistrarionFormResult result = new RegistrarionFormResult();
        if(isAdmin(request)) {
            Optional<RegistrationForm> byId = competitionFromRepository.findById(id);
            if (byId.isPresent()) {
                RegistrationForm registrationForm = byId.get();
                result.setRegistrationForm(registrationForm);
            }
        }
        return result;


    }

    public boolean isAdmin(HttpServletRequest request){
        Claims claims = (Claims) request.getAttribute("claims");
        String userName = claims.getSubject();
        if(userName.equals("admin")){
            return true;
        }else {
            return false;
        }
    }

    @GetMapping("/users")
    public  List<SysUser> getUser(HttpServletRequest request){
        //只有管理者才有权限
        List<SysUser> all=null;
        Claims claims = (Claims) request.getAttribute("claims");
        String userName = claims.getSubject();
        if(userName.equals("admin")){
            //是管理员，则返回成员列表
            all = registerRepository.findAll();
            Iterator<SysUser> iterator = all.iterator();
            while (iterator.hasNext()){
                SysUser next = iterator.next();
                if(next.getUserName().equals("admin")){
                    iterator.remove();
                }
            }

        }
        return all;
    }

    @GetMapping("/participants")
    public  List<RegistrationForm> getParticipants(HttpServletRequest request){
        //只有管理者才有权限
        List<RegistrationForm> all=null;
        Claims claims = (Claims) request.getAttribute("claims");
        String userName = claims.getSubject();
        if(userName.equals("admin")){
            //是管理员，则返回成员列表
            all = competitionFromRepository.findAll();

        }
        return all;
    }



    @PostMapping("/submitGrade")
    public void saveTeamGrade(@RequestBody GradeTeam gradeTeam,HttpServletRequest request){
        BaseResult result = new BaseResult();
        if(isAdmin(request)){
            Claims claims = (Claims) request.getAttribute("claims");
            String userName = claims.getSubject();
            //
            gradeTeam.setScorerName(userName);
            List<GradeTeam> list = teamGradeRepository.findByCaptionNameAndScorerName(gradeTeam.getCaptionName(), userName);
            if(list == null || list.size()==0){
                //表示还没评分
                teamGradeRepository.save(gradeTeam);
                result.setResult(true);
                result.setMessage("评价成功");
            }else {
                //可以评分
                result.setResult(false);
                result.setMessage("已经评价");
            }

        }
        //如果还没评分则保存
    }

    @PostMapping("/sentMail")
    public void sentMail(@RequestBody MailContent content,HttpServletRequest request){
        if(isAdmin(request)) {
            emailService.sentEmail("756343483@qq.com", content.getSubject(), content.getSubject());
        }
    }
    @GetMapping("/getExcel")
    public void getExcel(HttpServletResponse response) throws IOException{
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("报名表集合");

        List<RegistrationForm> registrationFormList = competitionFromRepository.findAll();

        String fileName = "userinf"  + ".xls";//设置要导出的文件的名字
        //新增数据行，并且设置单元格数据

        int rowNum = 1;

        String[] headers = { "id", "队长名", "队伍名", "电话号码"};
        //headers表示excel表中第一行的表头

        HSSFRow row = sheet.createRow(0);
        //在excel表中添加表头

        for(int i=0;i<headers.length;i++){
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

        //在表中存放查询到的数据放入对应的列
        for (RegistrationForm teacher : registrationFormList) {
            HSSFRow row1 = sheet.createRow(rowNum);
            row1.createCell(0).setCellValue(teacher.getId());
            row1.createCell(1).setCellValue(teacher.getCaptionName());
            row1.createCell(2).setCellValue(teacher.getDuiWuName());
            row1.createCell(3).setCellValue(teacher.getTelephone());
            rowNum++;
        }
       /* //生成文件
        File file = new File("Y://excel/");
        if(!file.exists()){
            file.mkdirs();
        }
        File file1 = new File("Y://excel/"+fileName);
        OutputStream out  = new FileOutputStream(file1);
        workbook.write(out);*/
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        //response.flushBuffer();
        workbook.write(response.getOutputStream());

    }







    @PostMapping("/joinUs")
    public boolean JoinUs(){
        return true;
    }


}

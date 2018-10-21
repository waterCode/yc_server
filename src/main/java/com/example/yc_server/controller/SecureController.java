package com.example.yc_server.controller;


import com.example.yc_server.domain.*;
import com.example.yc_server.repository.CompetitionFromRepository;
import com.example.yc_server.repository.JoinUsRepository;
import com.example.yc_server.repository.RegisterRepository;
import com.example.yc_server.repository.TeamGradeRepository;
import com.example.yc_server.service.EmailService;
import io.jsonwebtoken.Claims;
import org.apache.poi.hssf.usermodel.*;
import org.hibernate.mapping.Join;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/secure")
public class SecureController {


    private EntityManagerFactory emf;

    @Autowired
    private RegisterRepository registerRepository;

    @Autowired
    private CompetitionFromRepository competitionFromRepository;

    @Autowired
    private TeamGradeRepository teamGradeRepository;

    @Autowired
    private JoinUsRepository joinUsRepository;

    @Autowired
    private EmailService emailService;

    @PersistenceUnit//使用这个标记来注入EntityManagerFactory
    public void setEntityManagerFactory(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Value("${filePath}")
    private String prePath; //配置文件配置的物理保存地址

    @Value("${site}")
    private String site;

    @GetMapping("/getRegistrationForm")
    public BaseResult getRegistrationForm(@RequestParam(value = "id", required = true) Long id, HttpServletRequest request) {
        RegistrarionFormResult result = new RegistrarionFormResult();
        if (isAdmin(request)) {
            Optional<RegistrationForm> byId = competitionFromRepository.findById(id);
            if (byId.isPresent()) {
                RegistrationForm registrationForm = byId.get();
                result.setRegistrationForm(registrationForm);
                //查找附件，并返回
                String name = registrationForm.getCaptionName() + registrationForm.getWeChat();
                String path = prePath + name + "/photo";
                File file = new File(path);
                File[] files = file.listFiles();
                if (files != null) {
                    if (files[0] != null) {
                        String url = site + "/file/" + name + "/photo/" + files[0].getName();//返回图片url
                        result.setImgUrl(url);
                    }
                }
                String filePath = prePath + name + "/file";
                File fileZip = new File(filePath);
                File[] filesZip = fileZip.listFiles();
                if (filesZip != null) {
                    for (int i = 0; i < filesZip.length; i++) {
                        if (i == 0) {
                            String url = site + "/file/" + name + "/file/" + filesZip[0].getName();//返回图片url
                            result.setFile1Url(url);
                        } else if (i == 1) {
                            String url = site + "/file/" + name + "/file/" + filesZip[1].getName();//返回图片url
                            result.setFile2Url(url);
                        }
                    }

                }


            }
        }
        return result;


    }

    @GetMapping("/RegistrationForm/deleteById")
    public BaseResult deleteRegistrationFormById(@RequestParam(value = "id", required = true) Long id, HttpServletRequest request){
        BaseResult result = new BaseResult();
        if(isSuperAdmin(request)){
            Optional<RegistrationForm> byId = competitionFromRepository.findById(id);
            if(byId.isPresent()) {
                List<GradeTeam> byCaptionName = teamGradeRepository.findByCaptionName(byId.get().getCaptionName());
                if(byCaptionName!=null){
                    for(GradeTeam grade:byCaptionName){
                        teamGradeRepository.delete(grade);
                    }
                }
                competitionFromRepository.deleteById(id);

                result.setResult(true);
                result.setMessage("删除成功");
            }else {
                result.setResult(false);
                result.setMessage("该id不存在");
            }
        }else {
            result.setResult(false);
            result.setMessage("你不是超级管理员");
        }
        return result;
    }

    @GetMapping("/JoinUsForm/deleteById")
    public BaseResult deleteJoinUsFormById(@RequestParam(value = "id", required = true) Long id, HttpServletRequest request){
        BaseResult result = new BaseResult();
        if(isSuperAdmin(request)){
            joinUsRepository.deleteById(id);
            result.setResult(true);
            result.setMessage("删除成功");
        }else {
            result.setResult(false);
            result.setMessage("你不是超级管理员");
        }
        return result;
    }

    @GetMapping("/RegistrationForm/deleteAll")
    public BaseResult deleteRegistrationFormByAll(HttpServletRequest request){
        BaseResult result = new BaseResult();
        if(isSuperAdmin(request)){
            competitionFromRepository.deleteAll();
            teamGradeRepository.deleteAll();
            result.setResult(true);
            result.setMessage("删除成功");
        }else {
            result.setResult(false);
            result.setMessage("你不是超级管理员");
        }
        return result;
    }

    @GetMapping("/JoinUsForm/deleteAll")
    public BaseResult deleteJoinUsFormByAll(HttpServletRequest request){
        BaseResult result = new BaseResult();
        if(isSuperAdmin(request)){
            joinUsRepository.deleteAll();
            result.setResult(true);
            result.setMessage("删除成功");
        }else {
            result.setResult(false);
            result.setMessage("你不是超级管理员");
        }
        return result;
    }


    public boolean isAdmin(HttpServletRequest request) {
        // TODO: 2018/6/1 更改判断管理员逻辑
        Claims claims = (Claims) request.getAttribute("claims");
        String role = claims.getSubject();
        if (role.equals("admin") || role.equals("superAdmin")) {//
            return true;
        } else {
            return false;
        }
    }

    public boolean isSuperAdmin(HttpServletRequest request) {
        // TODO: 2018/6/1 更改判断管理员逻辑
        Claims claims = (Claims) request.getAttribute("claims");
        String role = claims.getSubject();
        if (role.equals("superAdmin")) {//
            return true;
        } else {
            return false;
        }
    }



    @GetMapping("/users")
    public GetUsersResult getUser(HttpServletRequest request) {
        //只有管理者才有权限
        List<SysUser> all = null;
        GetUsersResult result = new GetUsersResult();
        Claims claims = (Claims) request.getAttribute("claims");
        String role = claims.getSubject();
        if (role.equals("superAdmin")) {
            //是管理员，则返回成员列表
            all = registerRepository.findAll();
            Iterator<SysUser> iterator = all.iterator();
            while (iterator.hasNext()) {
                SysUser next = iterator.next();
                if (next.getUserName().equals("admin")) {
                    iterator.remove();
                }
            }
            result.setResult(true);
            result.setMessage("请求成功");
            result.setUsers(all);
        } else {
            result.setResult(false);
            result.setMessage("不是超级管理员，无权限");
        }
        return result;
    }

    @GetMapping("/joinUsMembers")
    public JoinUsResult getJoinUsMembers(HttpServletRequest request) {
        List<JoinUsForm> list = null;
        JoinUsResult result = new JoinUsResult();
        if (isAdmin(request)) {
            list = joinUsRepository.findAll();
            result.setJoinUsFormList(list);
            result.setResult(true);
            result.setMessage("获取成功");
        } else {
            result.setResult(false);
            result.setMessage("你没有权限");
        }
        return result;

    }

    @GetMapping("/participants")
    public ParticipantListResult getParticipants(HttpServletRequest request) {
        //只有管理者才有权限
        //RegistrationFormListResult result = new RegistrationFormListResult();
        ParticipantListResult result = new ParticipantListResult();
        List<RegistrationForm> all = null;
        if (isAdmin(request)) {
            //是管理员，则返回成员列表
            /*all = competitionFromRepository.findAll();
            result.setRegistrationFormList(all);
            result.setResult(true);
            result.setMessage("请求成功");*/

            EntityManager em = emf.createEntityManager();
            //加了这句就不会报错
           // em.getTransaction().begin();

            Query query = null;
            //定义SQL，可以多个表关联查询
            String sql = "select registration_form.caption_name,dui_wu_name,new_grade,other_grade,practice_grade,registration_form.id,telephone,zuo_pin_name from registration_form left join grade_team on registration_form.caption_name=grade_team.caption_name";
            //创建原生SQL查询QUERY实例
            query = em.createNativeQuery(sql);

            //执行查询，返回的是对象数组(Object[])列表,
            //每一个对象数组存的是相应的实体属性

            List resultList = query.getResultList();
            List<ParticipantList> participantLists = new ArrayList<>();
            for (int i = 0; i < resultList.size(); i++) {
                Object[] o = (Object[]) resultList.get(i);
                ParticipantList data = new ParticipantList();
                data.setCaptionName((String) o[0]);
                data.setDuiWuName((String) o[1]);
                if (o[2] != null) {
                    data.setNewGrade((Integer) o[2]);
                }
                if (o[3] != null)
                    data.setOtherGrade((Integer) o[3]);
                if (o[4] != null)
                    data.setPracticeGrade((Integer) o[4]);
                data.setId(Integer.parseInt( o[5].toString()));
                data.setPhoneNum((String) o[6]);
                data.setWorkNames((String)o[7]);
                participantLists.add(data);
            }
            result.setData(participantLists);
            result.setResult(true);
            em.close();

        } else {
            result.setResult(false);
            result.setMessage("你没有权限");
        }
        return result;
    }


    @PostMapping("/submitGrade")
    public BaseResult saveTeamGrade(@RequestBody GradeTeam gradeTeam, HttpServletRequest request) {
        BaseResult result = new BaseResult();
        if (isAdmin(request)) {
            Claims claims = (Claims) request.getAttribute("claims");
            String userName = claims.getSubject();
            //
            gradeTeam.setScorerName(userName);
            List<GradeTeam> list = teamGradeRepository.findByCaptionName(gradeTeam.getCaptionName());

            if (list == null || list.size() == 0) {
                //表示还没评分
                teamGradeRepository.save(gradeTeam);
                result.setResult(true);
                result.setMessage("评价成功");
            } else {
                //可以评分
                result.setResult(false);
                result.setMessage("该队伍已经评价过");
            }

        }
        return result;
        //如果还没评分则保存
    }

    @PostMapping("/sentMail")
    public BaseResult sentMail(@RequestBody MailContent content, HttpServletRequest request) {
        BaseResult result = new BaseResult();
        if (isAdmin(request)) {
            List<ToPeopleInfo> toPeople = content.getToPeople();
            if (toPeople != null && toPeople.size() > 0) {
                for (ToPeopleInfo people : toPeople) {
                    emailService.sentEmail(people.getEmail(), content.getSubject(), content.getSubject());
                }
            }
            result.setResult(true);
            result.setMessage("发送成功");
        } else {
            result.setResult(false);
            result.setMessage("你没有权限");
        }
        return result;
    }

    @GetMapping("/getExcel")
    public void getExcel(HttpServletRequest request,HttpServletResponse response) throws IOException {


        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("报名表集合");

        ParticipantListResult result = getParticipants(request);
        //List<RegistrationForm> registrationFormList = competitionFromRepository.findAll();

        if(result.isResult()) {
            String fileName = "userinf" + ".xls";//设置要导出的文件的名字
            //新增数据行，并且设置单元格数据

            int rowNum = 1;

            String[] headers = {"id","作品名","队长名", "队伍名", "电话号码", "创新分", "实践分", "其他分"};
            //headers表示excel表中第一行的表头

            HSSFRow row = sheet.createRow(0);
            //在excel表中添加表头

            for (int i = 0; i < headers.length; i++) {
                HSSFCell cell = row.createCell(i);
                HSSFRichTextString text = new HSSFRichTextString(headers[i]);
                cell.setCellValue(text);
            }

            List<ParticipantList> list = result.getData();
            //在表中存放查询到的数据放入对应的列
            for (ParticipantList info : list) {
                HSSFRow row1 = sheet.createRow(rowNum);
                row1.createCell(0).setCellValue(info.getId());
                row1.createCell(1).setCellValue(info.getWorkNames());
                row1.createCell(2).setCellValue(info.getCaptionName());
                row1.createCell(3).setCellValue(info.getDuiWuName());
                row1.createCell(4).setCellValue(info.getPhoneNum());
                row1.createCell(5).setCellValue(info.getNewGrade());
                row1.createCell(6).setCellValue(info.getPracticeGrade());
                row1.createCell(7).setCellValue(info.getOtherGrade());
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

    }

    @PostMapping("/updateIdentity")
    public BaseResult updateIdentity(@RequestBody SysUser user, HttpServletRequest request) {
        BaseResult baseResult = new BaseResult();
        if (isAdmin(request)) {
            //更新
            registerRepository.save(user);
            baseResult.setMessage("更新成功");
            baseResult.setResult(true);
        } else {
            baseResult.setMessage("管理员失败");
            baseResult.setResult(false);
        }
        return baseResult;

    }


    @PostMapping("/joinUs")
    public boolean JoinUs() {
        return true;
    }


}

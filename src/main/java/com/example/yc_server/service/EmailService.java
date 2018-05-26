package com.example.yc_server.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private JavaMailSender mailSender;


    public void sentEmail(String to,String subject,String content){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("810437416@qq.com");
        message.setTo("756343483@qq.com");
        message.setSubject(subject);
        message.setText(content);
        try {
            mailSender.send(message);//执行发送邮件
            logger.info("简单邮件已经发送。");
        } catch (Exception e) {
            logger.error("发送简单邮件时发生异常！", e);
        }
    }

}

package com.example.yc_server.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class RegistrationForm {

    @Id
    @GeneratedValue
    private Long id;
    private String captionName;
    private String zhuanYe;
    private String xueHao;
    private String telephone;
    private String weChat;
    private String School;
    private String duiWuName;
    private String zuoPinName;
    private String aboutTest;
    private String aboutFunction;
    private String aboutNews;
    private String technologyWay;
    private String technologyCase;
    private String productIntroduce;
    private String adress;


    public void setCaptionName(String captionName) {
        this.captionName = captionName;
    }

    public String getCaptionName() {
        return captionName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getZhuanYe() {
        return zhuanYe;
    }

    public void setZhuanYe(String zhuanYe) {
        this.zhuanYe = zhuanYe;
    }

    public String getXueHao() {
        return xueHao;
    }

    public void setXueHao(String xueHao) {
        this.xueHao = xueHao;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getWeChat() {
        return weChat;
    }

    public void setWeChat(String weChat) {
        this.weChat = weChat;
    }

    public String getSchool() {
        return School;
    }

    public void setSchool(String school) {
        School = school;
    }

    public String getDuiWuName() {
        return duiWuName;
    }

    public void setDuiWuName(String duiWuName) {
        this.duiWuName = duiWuName;
    }

    public String getZuoPinName() {
        return zuoPinName;
    }

    public void setZuoPinName(String zuoPinName) {
        this.zuoPinName = zuoPinName;
    }

    public String getAboutTest() {
        return aboutTest;
    }

    public void setAboutTest(String aboutTest) {
        this.aboutTest = aboutTest;
    }

    public String getAboutFunction() {
        return aboutFunction;
    }

    public void setAboutFunction(String aboutFunction) {
        this.aboutFunction = aboutFunction;
    }

    public String getAboutNews() {
        return aboutNews;
    }

    public void setAboutNews(String aboutNews) {
        this.aboutNews = aboutNews;
    }

    public String getTechnologyWay() {
        return technologyWay;
    }

    public void setTechnologyWay(String technologyWay) {
        this.technologyWay = technologyWay;
    }

    public String getTechnologyCase() {
        return technologyCase;
    }

    public void setTechnologyCase(String technologyCase) {
        this.technologyCase = technologyCase;
    }

    public String getProductIntroduce() {
        return productIntroduce;
    }

    public void setProductIntroduce(String productIntroduce) {
        this.productIntroduce = productIntroduce;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }
}

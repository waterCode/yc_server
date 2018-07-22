package com.example.yc_server.domain;

import java.util.List;

public class MailContent {
    private String subject;
    private String content;
    private List<ToPeopleInfo> toPeople;


    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<ToPeopleInfo> getToPeople() {
        return toPeople;
    }

    public void setToPeople(List<ToPeopleInfo> toPeople) {
        this.toPeople = toPeople;
    }
}

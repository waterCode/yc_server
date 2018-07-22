package com.example.yc_server.domain;

import java.util.List;

public class JoinUsResult extends BaseResult{
    private List<JoinUsForm> joinUsFormList;

    public List<JoinUsForm> getJoinUsFormList() {
        return joinUsFormList;
    }

    public void setJoinUsFormList(List<JoinUsForm> joinUsFormList) {
        this.joinUsFormList = joinUsFormList;
    }
}

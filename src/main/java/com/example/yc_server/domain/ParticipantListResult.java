package com.example.yc_server.domain;

import java.util.List;

public class ParticipantListResult extends BaseResult {

    private List<ParticipantList> data;

    public List<ParticipantList> getData() {
        return data;
    }

    public void setData(List<ParticipantList> data) {
        this.data = data;
    }
}

package com.example.yc_server.domain;

import java.util.List;

public class RegistrationFormListResult extends BaseResult{
    List<RegistrationForm> registrationFormList;

    public List<RegistrationForm> getRegistrationFormList() {
        return registrationFormList;
    }

    public void setRegistrationFormList(List<RegistrationForm> registrationFormList) {
        this.registrationFormList = registrationFormList;
    }


}

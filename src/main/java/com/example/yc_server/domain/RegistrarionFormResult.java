package com.example.yc_server.domain;

public class RegistrarionFormResult extends BaseResult{
    private RegistrationForm registrationForm;
    private String imgUrl;
    private String file1Url;
    private String file2Url;


    public RegistrarionFormResult(RegistrationForm registrationForm) {
        this.registrationForm = registrationForm;
    }


    public RegistrarionFormResult() {
    }

    public RegistrationForm getRegistrationForm() {
        return registrationForm;
    }

    public void setRegistrationForm(RegistrationForm registrationForm) {
        this.registrationForm = registrationForm;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getFile1Url() {
        return file1Url;
    }

    public void setFile1Url(String file1Url) {
        this.file1Url = file1Url;
    }

    public String getFile2Url() {
        return file2Url;
    }

    public void setFile2Url(String file2Url) {
        this.file2Url = file2Url;
    }
}

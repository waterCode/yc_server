package com.example.yc_server.domain;

public class BaseResult {

    private boolean result = false;
    private String message="";


    public boolean isResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}

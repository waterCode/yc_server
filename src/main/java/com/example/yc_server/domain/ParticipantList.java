package com.example.yc_server.domain;

public class ParticipantList {

    private String captionName;
    private String duiWuName;
    private int newGrade=-1;
    private int otherGrade=-1;
    private int practiceGrade=-1;

    public String getCaptionName() {
        return captionName;
    }

    public void setCaptionName(String captionName) {
        this.captionName = captionName;
    }

    public String getDuiWuName() {
        return duiWuName;
    }

    public void setDuiWuName(String duiWuName) {
        this.duiWuName = duiWuName;
    }

    public int getNewGrade() {
        return newGrade;
    }

    public void setNewGrade(int newGrade) {
        this.newGrade = newGrade;
    }

    public int getOtherGrade() {
        return otherGrade;
    }

    public void setOtherGrade(int otherGrade) {
        this.otherGrade = otherGrade;
    }

    public int getPracticeGrade() {
        return practiceGrade;
    }

    public void setPracticeGrade(int practiceGrade) {
        this.practiceGrade = practiceGrade;
    }
}

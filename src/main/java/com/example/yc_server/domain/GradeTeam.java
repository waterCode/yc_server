package com.example.yc_server.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class GradeTeam {

    @Id
    @GeneratedValue
    private Long id;
    private String captionName;
    private String scorerName;
    private int newGrade;
    private int otherGrade;
    private int practiceGrade;


    public String getCaptionName() {
        return captionName;
    }

    public void setCaptionName(String captionName) {
        this.captionName = captionName;
    }

    public String getScorerName() {
        return scorerName;
    }

    public void setScorerName(String scorerName) {
        this.scorerName = scorerName;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

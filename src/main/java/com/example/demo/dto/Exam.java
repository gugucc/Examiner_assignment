package com.example.demo.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity//考试
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String examName;//考试名称
    private String examCode;//考试代码
    private String interviewStarTime;//面试开始日期
    private String interviewEndTime;//面试结束日期

    protected Exam(){

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public String getExamCode() {
        return examCode;
    }

    public void setExamCode(String examCode) {
        this.examCode = examCode;
    }

    public String getInterviewStarTime() {
        return interviewStarTime;
    }

    public void setInterviewStarTime(String interviewStarTime) {
        this.interviewStarTime = interviewStarTime;
    }

    public String getInterviewEndTime() {
        return interviewEndTime;
    }

    public void setInterviewEndTime(String interviewEndTime) {
        this.interviewEndTime = interviewEndTime;
    }

    public Exam(String examName, String examCode, String interviewStarTime, String interviewEndTime) {
        this.examName = examName;
        this.examCode = examCode;
        this.interviewStarTime = interviewStarTime;
        this.interviewEndTime = interviewEndTime;
    }
}

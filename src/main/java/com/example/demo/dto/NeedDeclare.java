package com.example.demo.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity//需求申报
public class NeedDeclare {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ExamSiteName;//考点名称
    private String needer;//申报人
    private String examName;//考试名称
    private String examCode;//考试代码
    private String interviewStarTime;//面试开始日期
    private String interviewEndTime;//面试结束日期
    private String subjectName;//面试科目名
    private Long examinerNeed;//考官需求数量

    protected NeedDeclare(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExamSiteName() {
        return ExamSiteName;
    }

    public void setExamSiteName(String examSiteName) {
        ExamSiteName = examSiteName;
    }

    public String getNeeder() {
        return needer;
    }

    public void setNeeder(String needer) {
        this.needer = needer;
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

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Long getExaminerNeed() {
        return examinerNeed;
    }

    public void setExaminerNeed(Long examinerNeed) {
        this.examinerNeed = examinerNeed;
    }

    public NeedDeclare(String examSiteName, String needer, String examName, String examCode, String interviewStarTime, String interviewEndTime, String subjectName, Long examinerNeed) {
        ExamSiteName = examSiteName;
        this.needer = needer;
        this.examName = examName;
        this.examCode = examCode;
        this.interviewStarTime = interviewStarTime;
        this.interviewEndTime = interviewEndTime;
        this.subjectName = subjectName;
        this.examinerNeed = examinerNeed;
    }
}

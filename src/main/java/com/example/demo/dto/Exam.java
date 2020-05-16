package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity//考试
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String examName;//考试名称
    private String examCode;//考试代码
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "GMT+8")
    private Timestamp starTime;//面试开始日期
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "GMT+8")
    private Timestamp endTime;//面试结束日期
    private String studySubject;//科目名
    private String examType;//学段
    //private String absentExaminer;//缺席考官
    private Integer quantity;//考官申报的数量
    @ManyToOne//与考点实体多对一关系建立
    @JoinColumn(name = "exam_site_id",foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
    @JsonBackReference//禁止此字段序列化，防止死循环
    private ExamSite examSite;//所属考点
    @Transient//禁止此字段对应数据库
    private String ExamSiteName;

    private Integer state;//考试考官分配状态0没有分配；1智能分配；2手动分配

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

    public Timestamp getStarTime() {
        return starTime;
    }

    public void setStarTime(Timestamp starTime) {
        this.starTime = starTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public ExamSite getExamSite() {
        return examSite;
    }

    public void setExamSite(ExamSite examSite) {
        this.examSite = examSite;
    }

    public String getExamSiteName() {
        return ExamSiteName;
    }

    public void setExamSiteName(String examSiteName) {
        ExamSiteName = examSiteName;
    }


    public String getStudySubject() {
        return studySubject;
    }

    public void setStudySubject(String studySubject) {
        this.studySubject = studySubject;
    }

    public String getExamType() {
        return examType;
    }

    public void setExamType(String examType) {
        this.examType = examType;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Exam(String examName, String examCode, Timestamp starTime, Timestamp endTime, String studySubject, String examType, Integer quantity, ExamSite examSite, String examSiteName, Integer state) {
        this.examName = examName;
        this.examCode = examCode;
        this.starTime = starTime;
        this.endTime = endTime;
        this.studySubject = studySubject;
        this.examType = examType;
        this.quantity = quantity;
        this.examSite = examSite;
        ExamSiteName = examSiteName;
        this.state = state;
    }
}

package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//考点
@Entity
public class ExamSite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ExamSiteName;//考点名称
    private String district;
    @OneToMany(mappedBy = "examSite")//与考点建立一对多关系
    private List<Exam> exams = new ArrayList<>();
    private String Principal;//负责人
    private String PrnTel;//负责人电话


    protected ExamSite(){

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

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public List<Exam> getExams() {
        return exams;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }

    public String getPrincipal() {
        return Principal;
    }

    public void setPrincipal(String principal) {
        Principal = principal;
    }

    public String getPrnTel() {
        return PrnTel;
    }

    public void setPrnTel(String prnTel) {
        PrnTel = prnTel;
    }


    public ExamSite(String examSiteName, String district, List<Exam> exams, String principal, String prnTel) {
        ExamSiteName = examSiteName;
        this.district = district;
        this.exams = exams;
        Principal = principal;
        PrnTel = prnTel;
    }
}

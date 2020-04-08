package com.example.demo.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//面试科目
@Entity
public class DictStudySubject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String subjectName;//面试科目名
    private String subjectCode;//代码
    private Long subjectSortCode;//排序码

    protected DictStudySubject(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public Long getSubjectSortCode() {
        return subjectSortCode;
    }

    public void setSubjectSortCode(Long subjectSortCode) {
        this.subjectSortCode = subjectSortCode;
    }

    public DictStudySubject(String subjectName, String subjectCode, Long subjectSortCode) {
        this.subjectName = subjectName;
        this.subjectCode = subjectCode;
        this.subjectSortCode = subjectSortCode;
    }
}

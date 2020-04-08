package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

//考点
@Entity
public class ExamSite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ExamSiteName;//考点名称
    @Transient//禁止此字段对应数据库
    private String districtName;
    @ManyToOne//与区县实体多对一关系建立
    @JoinColumn(name = "district_id")
    @JsonBackReference//禁止此字段序列化，防止死循环
    private DictDistrict district;//所属区县

    private String Principal;//负责人
    private String PrnTel;//负责人电话
    private String examinerId;//考官id
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

    public DictDistrict getDistrict() {
        return district;
    }

    public void setDistrict(DictDistrict district) {
        this.district = district;
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

    public String getExaminerId() {
        return examinerId;
    }

    public void setExaminerId(String examinerId) {
        this.examinerId = examinerId;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public ExamSite(String examSiteName, String districtName, DictDistrict district, String principal, String prnTel, String examinerId) {
        ExamSiteName = examSiteName;
        this.districtName = districtName;
        this.district = district;
        Principal = principal;
        PrnTel = prnTel;
        this.examinerId = examinerId;
    }
}

package com.example.demo.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity//考官
public class Examiner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Excel(name = "考官姓名",orderNum = "0")
    private String examinerName;//考官姓名
    @Excel(name = "身份证号",orderNum = "1")
    private String idCard;//身份证号
    @Excel(name = "性别",orderNum = "2")
    private String sex;//性别
    @Excel(name = "民族",orderNum = "3")
    private String nation;//民族
    @Excel(name = "政治面貌",orderNum = "4")
    private String political;//政治面貌
    @Excel(name = "学段",orderNum = "5")
    private String examType;//学段
    @Excel(name = "学科代码",orderNum = "6")
    private String subjectName;//学科代码
    @Excel(name = "单位名称",orderNum = "7")
    private String companyName;//单位名称
    @Excel(name = "职务职称",orderNum = "8")
    private String position;//职务职称
    @Excel(name = "通信地址",orderNum = "9")
    private String mailingAddress;//通信地址
    @Excel(name = "固定电话",orderNum = "10")
    private String fixedTel;//固定电话
    @Excel(name = "手机号码",orderNum = "11")
    private String tel;//手机号码
    @Excel(name = "电子邮箱",orderNum = "12")
    private String email;//电子邮箱
    /*@ManyToOne//与区县实体多对一关系建立
    @JoinColumn(name = "district")
    @JsonBackReference//禁止此字段序列化，防止死循环*/
    @Excel(name = "考官所属区县",orderNum = "13")
    private String district;//考官所属区县
    @Excel(name = "状态",orderNum = "14")
    private String state;//状态
    @Transient//禁止此字段对应数据库
    private String districtName;

    public Examiner(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExaminerName() {
        return examinerName;
    }

    public void setExaminerName(String examinerName) {
        this.examinerName = examinerName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getPolitical() {
        return political;
    }

    public void setPolitical(String political) {
        this.political = political;
    }

    public String getExamType() {
        return examType;
    }

    public void setExamType(String examType) {
        this.examType = examType;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(String mailingAddress) {
        this.mailingAddress = mailingAddress;
    }

    public String getFixedTel() {
        return fixedTel;
    }

    public void setFixedTel(String fixedTel) {
        this.fixedTel = fixedTel;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    /* public DictDistrict getDistrict() {
        return district;
    }

    public void setDistrict(DictDistrict district) {
        this.district = district;
    }*/

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Examiner(String examinerName, String idCard, String sex, String nation, String political, String examType, String subjectName, String companyName, String position, String mailingAddress, String fixedTel, String tel, String email, String district, String state, String districtName) {
        this.examinerName = examinerName;
        this.idCard = idCard;
        this.sex = sex;
        this.nation = nation;
        this.political = political;
        this.examType = examType;
        this.subjectName = subjectName;
        this.companyName = companyName;
        this.position = position;
        this.mailingAddress = mailingAddress;
        this.fixedTel = fixedTel;
        this.tel = tel;
        this.email = email;
        this.district = district;
        this.state = state;
        this.districtName = districtName;
    }


    @Override
    public String toString() {
        return "Examiner{" +
                "id=" + id +
                ", examinerName='" + examinerName + '\'' +
                ", idCard='" + idCard + '\'' +
                ", sex='" + sex + '\'' +
                ", nation='" + nation + '\'' +
                ", political='" + political + '\'' +
                ", examType='" + examType + '\'' +
                ", subjectName='" + subjectName + '\'' +
                ", companyName='" + companyName + '\'' +
                ", position='" + position + '\'' +
                ", mailingAddress='" + mailingAddress + '\'' +
                ", fixedTel='" + fixedTel + '\'' +
                ", tel='" + tel + '\'' +
                ", email='" + email + '\'' +
                ", district=" + district +
                ", state='" + state + '\'' +
                ", districtName='" + districtName + '\'' +
                '}';
    }
}

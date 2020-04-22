package com.example.demo.dto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity//区县
public class DictDistrict {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String districtName;//区县名
    private String districtCode;//代码
    private Long districtSortCode;//排序码
    @OneToMany(mappedBy = "district")//与考点建立一对多关系
    private List<ExamSite> examSites = new ArrayList<>();
    /*@OneToMany(mappedBy = "district")//与考点建立一对多关系
    private List<Examiner> examiners = new ArrayList<>();*/

    protected DictDistrict(){

    }

    public DictDistrict(String districtName, String districtCode, Long districtSortCode, List<ExamSite> examSites) {
        this.districtName = districtName;
        this.districtCode = districtCode;
        this.districtSortCode = districtSortCode;
        this.examSites = examSites;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public Long getDistrictSortCode() {
        return districtSortCode;
    }

    public void setDistrictSortCode(Long districtSortCode) {
        this.districtSortCode = districtSortCode;
    }

    public List<ExamSite> getExamSites() {
        return examSites;
    }

    public void setExamSites(List<ExamSite> examSites) {
        this.examSites = examSites;
    }
}

package com.example.demo.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity//政治面貌
public class DictPolitical {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String politicalName;//政治面貌
    private String politicalCode;//代码
    private Long politicalSortCode;//排序码

    protected DictPolitical(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPoliticalName() {
        return politicalName;
    }

    public void setPoliticalName(String politicalName) {
        this.politicalName = politicalName;
    }

    public String getPoliticalCode() {
        return politicalCode;
    }

    public void setPoliticalCode(String politicalCode) {
        this.politicalCode = politicalCode;
    }

    public Long getPoliticalSortCode() {
        return politicalSortCode;
    }

    public void setPoliticalSortCode(Long politicalSortCode) {
        this.politicalSortCode = politicalSortCode;
    }

    public DictPolitical(String politicalName, String politicalCode, Long politicalSortCode) {
        this.politicalName = politicalName;
        this.politicalCode = politicalCode;
        this.politicalSortCode = politicalSortCode;
    }
}

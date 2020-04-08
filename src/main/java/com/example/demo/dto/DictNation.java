package com.example.demo.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity//名族
public class DictNation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nationName;//名族
    private String nationCode;//代码
    private Long nationSortCode;//排序码

    protected DictNation(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNationName() {
        return nationName;
    }

    public void setNationName(String nationName) {
        this.nationName = nationName;
    }

    public String getNationCode() {
        return nationCode;
    }

    public void setNationCode(String nationCode) {
        this.nationCode = nationCode;
    }

    public Long getNationSortCode() {
        return nationSortCode;
    }

    public void setNationSortCode(Long nationSortCode) {
        this.nationSortCode = nationSortCode;
    }

    public DictNation(String nationName, String nationCode, Long nationSortCode) {
        this.nationName = nationName;
        this.nationCode = nationCode;
        this.nationSortCode = nationSortCode;
    }
}

package com.example.demo.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//学段
@Entity
public class DictExamType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String typeName;//学段
    private String typeCode;//代码
    private Long typeSortCode;//排序码

    protected DictExamType(){

    }

    public DictExamType(String typeName, String typeCode, Long typeSortCode) {
        this.typeName = typeName;
        this.typeCode = typeCode;
        this.typeSortCode = typeSortCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public Long getTypeSortCode() {
        return typeSortCode;
    }

    public void setTypeSortCode(Long typeSortCode) {
        this.typeSortCode = typeSortCode;
    }
}

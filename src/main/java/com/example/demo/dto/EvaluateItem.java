package com.example.demo.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//考官评价的备选项
@Entity
public class EvaluateItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String itemName;//评价项
    private String score;//分值
    private Long itemSortCode;//排序码
    private Long evaluateTargetId;//评价维度

    protected EvaluateItem(){

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Long getItemSortCode() {
        return itemSortCode;
    }

    public void setItemSortCode(Long itemSortCode) {
        this.itemSortCode = itemSortCode;
    }

    public Long getEvaluateTargetId() {
        return evaluateTargetId;
    }

    public void setEvaluateTargetId(Long evaluateTargetId) {
        this.evaluateTargetId = evaluateTargetId;
    }

    public EvaluateItem(String itemName, String score, Long itemSortCode, Long evaluateTargetId) {
        this.itemName = itemName;
        this.score = score;
        this.itemSortCode = itemSortCode;
        this.evaluateTargetId = evaluateTargetId;
    }
}

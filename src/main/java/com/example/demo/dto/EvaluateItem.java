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
    private String evaluateName;//
    private String optionA;//评价项A
    private String optionB;//评价项B
    private String optionC;//评价项C

    protected EvaluateItem(){

    }

    public EvaluateItem(String evaluateName, String optionA, String optionB, String optionC) {
        this.evaluateName = evaluateName;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEvaluateName() {
        return evaluateName;
    }

    public void setEvaluateName(String evaluateName) {
        this.evaluateName = evaluateName;
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }
}

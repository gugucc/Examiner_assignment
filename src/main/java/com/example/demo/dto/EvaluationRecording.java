package com.example.demo.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class EvaluationRecording {//评价记录表
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String principal;
    private String exam;
    private String examiner;
    //选择的选项：概念的讲解-非常好,迟到早退问题-经常迟到早退
    private String selectedContent;
    private String score;
    public EvaluationRecording(){

    }

    public EvaluationRecording(String principal, String exam, String examiner, String selectedContent, String score) {
        this.principal = principal;
        this.exam = exam;
        this.examiner = examiner;
        this.selectedContent = selectedContent;
        this.score = score;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getExam() {
        return exam;
    }

    public void setExam(String exam) {
        this.exam = exam;
    }

    public String getExaminer() {
        return examiner;
    }

    public void setExaminer(String examiner) {
        this.examiner = examiner;
    }

    public String getSelectedContent() {
        return selectedContent;
    }

    public void setSelectedContent(String selectedContent) {
        this.selectedContent = selectedContent;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}

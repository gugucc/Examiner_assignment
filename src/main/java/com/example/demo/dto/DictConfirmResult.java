package com.example.demo.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity//缺席原因
public class DictConfirmResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String confirmResultName;//缺席原因
    private Long confirmResultCode;//代码

    protected DictConfirmResult(){

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConfirmResultName() {
        return confirmResultName;
    }

    public void setConfirmResultName(String confirmResultName) {
        this.confirmResultName = confirmResultName;
    }

    public Long getConfirmResultCode() {
        return confirmResultCode;
    }

    public void setConfirmResultCode(Long confirmResultCode) {
        this.confirmResultCode = confirmResultCode;
    }

    public DictConfirmResult(String confirmResultName, Long confirmResultCode) {
        this.confirmResultName = confirmResultName;
        this.confirmResultCode = confirmResultCode;
    }
}

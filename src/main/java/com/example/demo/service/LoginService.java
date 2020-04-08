package com.example.demo.service;


import com.example.demo.dto.Admin;
import com.example.demo.dto.ExamSite;

public interface LoginService {
    /**
     * 管理员登陆
     *
     * @param username
     * @return
     */
    Admin AdminLogin(String username);

    /**
     * 考点登录
     * @param PrnTel
     * @return
     */
    ExamSite examsiteLogin(String PrnTel);
}

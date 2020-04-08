package com.example.demo.service;


import com.example.demo.dto.Admin;

public interface AdminService {
    Admin login();
     //通过密码查询管理员
    Admin findOneByPassword(String oldPassword);

    //保存管理员
    void save(Admin admin);

}

package com.example.demo.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page")
//admin主要页面获取
public class AdminMainController {
    @RequestMapping("/welcome")
    public String welcome_1(){
        return "admin/welcome.html";
    }
    @RequestMapping("/user-setting")
    public String user_setting(){
        return "admin/user-setting";
    }
    @RequestMapping("/user-password")
    public String user_password(){
        return "admin/user-password.html";
    }
    @RequestMapping("/student/list")
    public String user_list(){
        return "admin/student-list.html";
    }
    @RequestMapping("/student/edit")
    public String user_edit(){
        return "admin/student-edit.html";
    }
    @RequestMapping("/examSite/list")
    public String examSite_list(){
        return "admin/examSite-list.html";
    }
    @RequestMapping("/confirmResult/list")
    public String department_list(){
        return "admin/confirmResult-list.html";
    }
    @RequestMapping("/examType/list")
    public String examType_list(){
        return "admin/examType-list.html";
    }
    @RequestMapping("/district/list")
    public String district_list(){
        return "admin/district-list.html";
    }
    @RequestMapping("/studySubject/list")
    public String studySubject_list(){
        return "admin/studySubject-list.html";
    }
    @RequestMapping("/teachingManagement/list")
    public String teachingManagement(){
        return "admin/teaching-management-list.html";
    }
    @RequestMapping("/examiner/list")
    public String examiner_list(){
        return "admin/examiner-list.html";
    }
    @RequestMapping("/view")
    public String view(){
        return "admin/view2.html";
    }
}

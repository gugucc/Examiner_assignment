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
    @RequestMapping("/examSite/list")
    public String examSite_list(){
        return "admin/examSite-list.html";
    }
    @RequestMapping("/exam/list")
    public String exam_list(){
        return "admin/exam-list.html";
    }
    @RequestMapping("/evaluateItem/list")
    public String evaluateItem_list(){
        return "admin/evaluateItem-list.html";
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
    @RequestMapping("/examiner/list")
    public String examiner_list(){
        return "admin/examiner-list.html";
    }
    @RequestMapping("/examinerStatus/list")
    public String examinerStatus_list(){
        return "admin/examinerStatus-list.html";
    }
    @RequestMapping("/evaluationRecording/list")
    public String evaluationRecording_list(){
        return "admin/evaluationRecording-list.html";
    }
    @RequestMapping("/viewExaminer/list")
    public String viewExaminer_list(){
        return "admin/viewExaminer-list.html";
    }
    @RequestMapping("/view")
    public String view(){
        return "admin/view2.html";
    }
    @RequestMapping("/insert-admin")
    public String insertAdmin(){
        return "admin/insert-admin.html";
    }
}

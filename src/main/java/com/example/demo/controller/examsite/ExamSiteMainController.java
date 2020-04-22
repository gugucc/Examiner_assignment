package com.example.demo.controller.examsite;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/page")
public class ExamSiteMainController {
    @RequestMapping("/chargeExam/list")
    public String chargeExam(){
        return "examSites/chargeExam-list.html";
    }
    @RequestMapping("/endExam/list")
    public String endExam_list(){ return "examSites/endExam-list.html"; }
   /* @RequestMapping("/examinerDiclare")
    public String examinerDiclare(){
        return "examSites/examinerDiclare.html";
    }*/
}

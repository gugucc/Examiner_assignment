package com.example.demo.comment;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//强制跳转到登录
public class MainController {

    //登录页面跳转
    @RequestMapping("/")
    public String index(){
        return "redirect:login";
    }

}

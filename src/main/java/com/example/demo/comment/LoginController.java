package com.example.demo.comment;;


import com.example.demo.dto.Admin;
import com.example.demo.dto.ExamSite;
import com.example.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView getLogin(HttpServletRequest request, /*获取一次性数据*/@ModelAttribute("msg") String msg, Model model) {
        //判断用户是否已经登录，跳转相应页面
        if (request.getSession().getAttribute("user") != null) {
            if (request.getSession().getAttribute("user") instanceof Admin){
                return new ModelAndView("redirect:admin");
            }else if (request.getSession().getAttribute("user") instanceof ExamSite){
                return new ModelAndView("redirect:teacher");
            }
        }
        //未登录跳转登录页面
        return new ModelAndView("login.html");
    }


    //登录的方法post提交
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String postLogin(RedirectAttributes model, HttpServletRequest request, String username, String userCode, String password) {
        String code = (String) request.getSession().getAttribute("code");
        if (code == null) {
            model.addFlashAttribute("msg", "服务器错误");
            return "redirect:login";
        }
        if (!code.equals(userCode.toLowerCase())) {
            model.addFlashAttribute("msg", "验证码错误！");
            return "redirect:login";

        }
        //判断此对象是否存在
        if (loginService.AdminLogin(username) != null) {
            Admin admin = loginService.AdminLogin(username);
            //如果存在再对比密码
            if (admin.getPassword().equals(password)) {
                //跳转登录成功页面并给session赋值用户
                request.getSession().setAttribute("user", admin);
                model.addFlashAttribute("msg","欢迎"+admin.getUsername()+"考务管理员!");
                return "redirect:admin";
            } else {
                model.addFlashAttribute("msg", "用户名或密码错误");
            }

        } else if (loginService.examsiteLogin(username) != null) {
            ExamSite examSite = loginService.examsiteLogin(username);
            //如果存在则对比密码
            if (examSite.getPrnTel().equals(password)){
                //跳转登录成功页面并给session赋值用户
                request.getSession().setAttribute("user",examSite);
                model.addFlashAttribute("msg","欢迎"+examSite.getPrincipal()+examSite.getExamSiteName()+"考点管理员!");
                return "redirect:examsiter";
            }
            else {
                model.addFlashAttribute("msg", "用户名或密码错误");
            }

        }
        else {
            model.addFlashAttribute("msg", "此用户不存在");
        }
        return "redirect:login";
    }
}

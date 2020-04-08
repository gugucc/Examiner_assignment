package com.example.demo.controller.examsiter;

import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.example.demo.dto.Admin;
import com.example.demo.dto.Examiner;
import com.example.demo.service.ExaminerService;
import com.example.demo.service.LoginService;
import com.example.demo.utils.EasyPoiUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ExamsiterController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private ExaminerService examinerService;

    @RequestMapping("/examsiter")
    public ModelAndView index(@ModelAttribute("msg") String msg, Model model, HttpServletRequest request, String username) {
        model.addAttribute("msg", msg);
        Admin admin = loginService.AdminLogin(username);

        return new ModelAndView("examsiter/index");
    }

    /**
     * 测试单sheet导出
     *
     * @throws IOException
     */
    @RequestMapping("/ExporExcel")
    @ResponseBody
    public void examinerExportExcel() throws IOException {
        List<Examiner> content = examinerService.findAllList();
        System.out.println(content);
        EasyPoiUtils.exportExcel(Examiner.class, content, "src/main/resources/excel/", "考官信息表.xls");
    }



}

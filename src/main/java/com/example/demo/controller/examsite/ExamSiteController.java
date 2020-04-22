package com.example.demo.controller.examsite;

import com.example.demo.dto.*;
import com.example.demo.service.*;
import com.example.demo.utils.EasyPoiUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ExamSiteController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private ExaminerService examinerService;
    @Autowired
    private ExamService examService;
    @Autowired
    private ExamSiteService examSiteService;
    @Autowired
    private ExamTypeService examTypeService;
    @Autowired
    private StudySubjectService studySubjectService;
    @Autowired
    private EvaluateItemService evaluateItemService;
    @Autowired
    private EvaluationRecordingService evaluationRecordingService;

    @RequestMapping("/examSites")
    public ModelAndView index(@ModelAttribute("msg") String msg, Model model, HttpServletRequest request, String username) {
        model.addAttribute("msg", msg);
        if (!(request.getSession().getAttribute("user") instanceof ExamSite)){
            return new ModelAndView("error/403");
        }
        return new ModelAndView("examSites/index");
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

    //考试列表获取
    @GetMapping("/chargeExamList")
    @ResponseBody
    public Map<String,Object> chargeExamList(Integer page, Integer limit,HttpServletRequest request){
        page--;
        Pageable pageable = PageRequest.of(page,limit);
        ExamSite examSite= (ExamSite) request.getSession().getAttribute("user");
        List<Exam> content = examService.findAllExamByPrincipal(examSite.getId(),pageable).getContent();
        Map<String,Object> map = new HashMap<>();
        for (Exam s:content
        ) {
            s.setExamSiteName(s.getExamSite().getExamSiteName());
        }
        map.put("data",content);
        map.put("size",examService.findAllCountPrincipal(examSite.getId()));
        return map;
    }


    //考官申报页面
    @GetMapping("/examinerDeclare")
    public ModelAndView toExaminerDeclare(Long id,Model model){

        List<ExamSite> examSiteList = examSiteService.findAllList();
        List<DictStudySubject> studySubjectList = studySubjectService.findAllList();
        List<DictExamType> examTypeList = examTypeService.findAllList();
        Exam exam = examService.findById(id);
        model.addAttribute("examSite",exam.getExamSite().getExamSiteName());
        model.addAttribute("exam",exam);
        model.addAttribute("examSiteList",examSiteList);
        model.addAttribute("studySubjectList",studySubjectList);
        model.addAttribute("examTypeList",examTypeList);
        return new ModelAndView("examSites/examinerDeclare.html","model",model);
    }
    //考官申报方法
    @PostMapping("/examinerDeclare")
    @ResponseBody
    public String examinerDeclare(Exam exam,Long examSite){
        Exam examNew = examService.findById(exam.getId());
            if ((exam.getQuantity()) == null) {
                return "equals";
            } else {
                examNew.setQuantity(exam.getQuantity());
                examService.saveOne(examNew);
                examService.updateState(0, exam.getId());//state:0代表考官申报完成
                return "ok";
            }
    }

    //考官确认页面获取
    @GetMapping("/statusConfirmationPage")
    public ModelAndView viewExaminerPage(Long id,Model model){
        Exam exam=examService.findById(id);
        model.addAttribute("exam",exam);
        System.out.println(id);
        return new ModelAndView("examSites/statusConfirmation.html","model",model);
    }

    //考官确认界面
    @GetMapping("/statusConfirmation")
    @ResponseBody
    public Map<String,Object> viewExaminer(Long id,Integer page,Integer limit){
        page--;
        Pageable pageable = PageRequest.of(page,limit);
        List<Examiner> content = examinerService.findViewExaminer(id,pageable).getContent();
        Map<String,Object> map = new HashMap<>();
        map.put("data",content);
        map.put("size",examinerService.findAllCountViewExaminer(id));
        return map;
    }

    //获取考试结束列表，考官评价
    @GetMapping("/endExam")
    @ResponseBody
    public Map<String, Object> endExam(Integer page, Integer limit,HttpServletRequest request) {
        page--;
        Pageable pageable = PageRequest.of(page, limit);
        ExamSite examSite= (ExamSite) request.getSession().getAttribute("user");
        List<Exam> content = examService.findEndExam(examSite.getId(),pageable).getContent();
        Map<String, Object> map = new HashMap<>();
        for (Exam s : content
        ) {
            s.setExamSiteName(s.getExamSite().getExamSiteName());
        }
        int count =examService.findAllEndExam(examSite.getId());
        System.out.println(count);
        if (count==0){
            map.put("msg","no" );
            return map;
        }else{
            map.put("data", content);
            map.put("size",examService.findAllEndExam(examSite.getId()));
            return map;
        }
    }

    //分配的考官页面获取
    @GetMapping("/endExamExaminerPage")
    public ModelAndView endExamExaminerPage(Long id, Model model) {
        Exam exam = examService.findById(id);
        model.addAttribute("exam", exam);
        System.out.println(id);
        return new ModelAndView("examSites/endExamExaminer-list.html", "model", model);
    }

    //获取结束考试的考官
    @GetMapping("/endExamExaminer")
    @ResponseBody
    public Map<String, Object> endExamExaminer(Long id, Integer page, Integer limit) {
        page--;
        Pageable pageable = PageRequest.of(page, limit);
        List<Examiner> content = examinerService.findViewExaminer(id, pageable).getContent();
        Map<String, Object> map = new HashMap<>();
        map.put("data", content);
        map.put("size", examinerService.findAllCountViewExaminer(id));
        return map;
    }

    @RequestMapping("/do")
    public ModelAndView test(Model model,Long id,Long ids){
        System.out.println(id);
        List<EvaluateItem> evaluateItemList = evaluateItemService.findAllList();
        Examiner examiner = examinerService.findById(id);
        model.addAttribute("evaluateItemList",evaluateItemList);
        model.addAttribute("examiner",examiner);
        model.addAttribute("ids",ids);
        return new ModelAndView("examSites/evaluation.html","model",model);
    }


    @RequestMapping("/save")
    @ResponseBody
    public String save(String score,String selectedContent,HttpServletRequest request,Long id){
        EvaluationRecording evaluationRecording = new EvaluationRecording();
        ExamSite examSite= (ExamSite) request.getSession().getAttribute("user");
        Examiner examiner = examinerService.findById(id);
        Long ids=Long.valueOf(examiner.getExamId());
        Exam exam=examService.findById(ids);
            evaluationRecording.setPrincipal(examSite.getPrincipal());
            evaluationRecording.setExam(exam.getExamName());
            evaluationRecording.setExaminer(examiner.getExaminerName());
            evaluationRecording.setScore(score);
            evaluationRecording.setSelectedContent(selectedContent);

        try {
            evaluationRecordingService.save(evaluationRecording);
            examinerService.updateEvaluationState(id);
            return "ok";

        }
        catch (Exception e){
            return "no";
        }
    }


}

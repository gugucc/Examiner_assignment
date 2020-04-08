package com.example.demo.controller.admin;

import com.example.demo.dto.*;
import com.example.demo.service.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.*;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private ExaminerService examinerService;
    @Autowired
    private NationService nationService;
    @Autowired
    private PoliticalService politicalService;
    @Autowired
    private ExamTypeService examTypeService;
    @Autowired
    private StudySubjectService studySubjectService;
    @Autowired
    private DistrictService districtService;
    @Autowired
    private ConfirmResultService confirmResultService;
    @Autowired
    private ExamSiteService examSiteService;

    //管理员登录后主页面
    @RequestMapping(value = "/admin",method = RequestMethod.GET)
    public ModelAndView index(@ModelAttribute("msg") String msg, Model model, HttpServletRequest request){
        model.addAttribute("msg", msg);
        if (!(request.getSession().getAttribute("user") instanceof Admin)){
            return new ModelAndView("error/403");
        }
        return new ModelAndView("admin/index");
    }

    //管理员修改账号（信息，密码等）
    @PostMapping(value = "/admin")
    @ResponseBody
    public String changePassword(String old_password, String new_password) {
        Admin admin = adminService.findOneByPassword(old_password);
        if (admin != null){
            admin.setPassword(new_password);
            adminService.save(admin);
            return "ok";
        }else if (admin == null){
            return "no";
        }
        return "fail";
    }


    //考官列表获取
    @GetMapping("/examiner")
    @ResponseBody
    public Map<String,Object> examiner(Integer page, Integer limit){
        page--;
        Pageable pageable = PageRequest.of(page,limit);
        List<Examiner> content = examinerService.findAll(pageable).getContent();
        Map<String,Object> map = new HashMap<>();
        /*for (Examiner s:content
        ) {
            s.setDistrictName(s.getDistrict().getDistrictName());
        }*/
        map.put("data",content);
        map.put("size",examinerService.findAllCount());
        return map;
    }



    //修改考官信息：页面
    @GetMapping("/editExaminer")
    public ModelAndView editExaminerPage(Long id,Model model){
        Examiner examiner = examinerService.findById(id);
        List<DictNation> nationList = nationService.findAllList();
        List<DictPolitical> politicalList = politicalService.findAllList();
        List<DictExamType> examTypeList = examTypeService.findAllList();
        List<DictStudySubject> studySubjectList = studySubjectService.findAllList();
        List<DictDistrict> districtList = districtService.findAllList();
        model.addAttribute("examiner",examiner);
        model.addAttribute("nationList",nationList);
        model.addAttribute("politicalList",politicalList);
        model.addAttribute("examTypeList",examTypeList);
        model.addAttribute("studySubjectList",studySubjectList);
        model.addAttribute("districtList",districtList);

        /*model.addAttribute("district",examiner.getDistrict().getDistrictName());*/
        System.out.println(id);
        return new ModelAndView("admin/examinerForm.html","model",model);
    }
    //方法
    @PostMapping("/editExaminer")
    @ResponseBody
    public String editExaminer(Examiner examiner,Long nationId,Long politicalId,Long examTypeId,Long studySubjectId,Long district){

        System.out.println(examiner.getId());
        Examiner examinerNew = examinerService.findById(examiner.getId());
        DictNation nation = nationService.findById(nationId);
        DictPolitical political = politicalService.findById(politicalId);
        DictExamType examType = examTypeService.findById(examTypeId);
        DictStudySubject studySubject = studySubjectService.findById(studySubjectId);
        DictDistrict district1 = districtService.findById(district);
        if (examinerNew.getExaminerName().equals(examiner.getExaminerName())) {
            if(examinerNew.getIdCard().equals(examiner.getIdCard())){
                    if (examinerNew.getSex().equals(examiner.getSex())){
                        if (examinerNew.getNation().equals(nation.getNationName())){
                            if (examinerNew.getPolitical().equals(political.getPoliticalName())){
                                if (examinerNew.getExamType().equals(examType.getTypeName())) {
                                    if (examinerNew.getSubjectName().equals(studySubject.getSubjectName())) {
                                        if (examinerNew.getCompanyName().equals(examiner.getCompanyName())) {
                                            if (examinerNew.getPosition().equals(examiner.getPosition())) {
                                                if (examinerNew.getMailingAddress().equals(examiner.getMailingAddress())) {
                                                    if (examinerNew.getFixedTel().equals(examiner.getFixedTel())) {
                                                        if (examinerNew.getTel().equals(examiner.getTel())) {
                                                            if (examinerNew.getEmail().equals(examiner.getEmail())) {
                                                                if (examinerNew.getDistrict().equals(examiner.getDistrict())){
                                                                    /*if (examinerNew.getDistrict().getDistrictName().equals(examiner.getDistrict().getDistrictName())) {*/
                                                                    if (examinerNew.getState().equals(examiner.getState())) {
                                                                            return "noEdit";

                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
            }
            examinerNew.setExaminerName(examiner.getExaminerName());
            examinerNew.setIdCard(examiner.getIdCard());
            examinerNew.setSex(examiner.getSex());
            examinerNew.setNation(nation.getNationName());
            examinerNew.setPolitical(political.getPoliticalName());
            examinerNew.setExamType(examType.getTypeName());
            examinerNew.setSubjectName(studySubject.getSubjectName());
            examinerNew.setCompanyName(examiner.getCompanyName());
            examinerNew.setMailingAddress(examiner.getMailingAddress());
            examinerNew.setFixedTel(examiner.getFixedTel());
            examinerNew.setTel(examiner.getTel());
            examinerNew.setEmail(examiner.getEmail());
            examinerNew.setDistrict(district1.getDistrictName());
            examinerNew.setState(examiner.getState());
            examinerService.saveOne(examinerNew);
            System.out.println(examiner.getEmail());
            return "ok";
        } else {
            if (examinerService.findOneByIdCard(examiner.getIdCard()) != null) {
                return "equals";
            } else {
                examinerNew.setExaminerName(examiner.getExaminerName());
                examinerNew.setIdCard(examiner.getIdCard());
                examinerNew.setSex(examiner.getSex());
                examinerNew.setNation(nation.getNationName());
                examinerNew.setPolitical(political.getPoliticalName());
                examinerNew.setExamType(examType.getTypeName());
                examinerNew.setSubjectName(studySubject.getSubjectName());
                examinerNew.setCompanyName(examiner.getCompanyName());
                examinerNew.setMailingAddress(examiner.getMailingAddress());
                examinerNew.setFixedTel(examiner.getFixedTel());
                examinerNew.setTel(examiner.getTel());
                examinerNew.setEmail(examiner.getEmail());
                examinerNew.setDistrict(district1.getDistrictName());
                examinerNew.setState(examiner.getState());
                examinerService.saveOne(examinerNew);
                return "ok";

            }
        }

    }



    //添加考官的界面
    @GetMapping("/addExaminer")
    public ModelAndView addExaminer(Model model){
        List<DictNation> nationList = nationService.findAllList();
        List<DictPolitical> politicalList = politicalService.findAllList();
        List<DictExamType> examTypeList = examTypeService.findAllList();
        List<DictStudySubject> studySubjectList = studySubjectService.findAllList();
        List<DictDistrict> districtList = districtService.findAllList();
        model.addAttribute("nationList",nationList);
        model.addAttribute("politicalList",politicalList);
        model.addAttribute("examTypeList",examTypeList);
        model.addAttribute("studySubjectList",studySubjectList);
        model.addAttribute("districtList",districtList);
       // model.addAttribute("cgClassList",cgClassList);

        return new ModelAndView("admin/examinerAdd.html","model",model);
    }
    //添加考官的方法
    @PostMapping("/addExaminer")
    @ResponseBody
    public String addExaminer(Examiner examiner,Long nationId,Long politicalId,Long examTypeId,Long studySubjectId,Long districtId){
        DictNation nation = nationService.findById(nationId);
        DictPolitical political = politicalService.findById(politicalId);
        DictExamType examType = examTypeService.findById(examTypeId);
        DictStudySubject studySubject = studySubjectService.findById(studySubjectId);
        DictDistrict district = districtService.findById(districtId);
        //判断身份证号是否存在
        if (examinerService.findOneByIdCard(examiner.getIdCard()) !=null){
//            System.out.println(666);
            return "equals";
        }
        examiner.setExaminerName(examiner.getExaminerName());
        examiner.setIdCard(examiner.getIdCard());
        examiner.setSex(examiner.getSex());
        examiner.setNation(examiner.getNation());
        examiner.setPolitical(examiner.getPolitical());
        examiner.setExamType(examiner.getExamType());
        examiner.setSubjectName(examiner.getSubjectName());
        examiner.setCompanyName(examiner.getCompanyName());
        examiner.setPosition(examiner.getPosition());
        examiner.setMailingAddress(examiner.getMailingAddress());
        examiner.setFixedTel(examiner.getFixedTel());
        examiner.setTel(examiner.getTel());
        examiner.setEmail(examiner.getEmail());
        examiner.setDistrict(examiner.getDistrict());
        examiner.setState(examiner.getState());
        try{
            examinerService.saveOne(examiner);
            return "ok";
        } catch (Exception e){
            return "no";
        }

    }


    //删除单个考官
    @PostMapping("/deleteExaminer")
    @ResponseBody
    public int deleteExaminer(int id){
        int i =examinerService.deleteExaminer(id);
        System.out.println("======"+id);
        return i;
    }
    //删除多个学考官
    @PostMapping("/deleteAllExaminer")
    @ResponseBody
    public int deleteAllExaminer(@RequestParam("id") String id){
        // 接收包含stuId的字符串，并将它分割成字符串数组
        String[] stuList = id.split(",");
        // 将字符串数组转为List<Intger> 类型
        List<Long> LString = new ArrayList<Long>();
        for(String str : stuList){
            LString.add(new Long(str));
        }
        System.out.println("====="+LString);
        // 调用service层的批量删除函数
        int i = examinerService.deleteAllExaminer(LString);
        return i;
    }


    //搜索考官
    @GetMapping("/LikeExaminer")
    @ResponseBody
    public Map<String,Object> examiner(Integer page, Integer limit,Examiner examiner){
        page--;
        Pageable pageable = PageRequest.of(page,limit);
        List<Examiner> content = examinerService.findAll(new Specification<Examiner>(){
            @Override
            public Predicate toPredicate(Root<Examiner> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<>();
                //根据examinerName 模糊查询 examiner
                if (StringUtils.isNotBlank(examiner.getExaminerName())) {
                    list.add(cb.like(root.get("examinerName").as(String.class), "%" + examiner.getExaminerName() + "%"));
                }
                //根据subjectCode 查询 examiner
                if (StringUtils.isNotBlank(examiner.getSubjectName())) {
                    list.add(cb.like(root.get("subjectCode").as(String.class), "%"+ examiner.getSubjectName()+ "%"));
                }
                //根据examType 查询 examiner
                if (StringUtils.isNotBlank(examiner.getExamType())) {
                    list.add(cb.like(root.get("examType").as(String.class), "%"+ examiner.getExamType()+ "%"));
                }
                //根据idCard 查询 examiner
                if (StringUtils.isNotBlank(examiner.getIdCard())) {
                    list.add(cb.like(root.get("idCard").as(String.class), "%"+ examiner.getIdCard()+ "%"));
                }
                //根据examinerDistrict 查询 examiner
                if (StringUtils.isNotBlank(examiner.getDistrictName())) {
                    Join<DictDistrict, Examiner> join = root.join("department", JoinType.LEFT);
                    list.add(cb.like(join.get("dptName"),"%"+ examiner.getDistrictName()+ "%"));
                }
                Predicate[] pre = new Predicate[list.size()];
                criteriaQuery.where(list.toArray(pre));
                return cb.and(list.toArray(pre));
            }
        },pageable).getContent();
        Map<String,Object> map = new HashMap<>();

        map.put("data",content);
        map.put("size",examinerService.findAllCount());
        return map;
    }


    //学段列表获取
    @GetMapping("/examType")
    @ResponseBody
    public Map<String,Object> examType(Integer page, Integer limit){
        page--;
        Pageable pageable = PageRequest.of(page,limit);
        List<DictExamType> content = examTypeService.findAll(pageable).getContent();
        Map<String,Object> map = new HashMap<>();
        map.put("data",content);
        map.put("size",examTypeService.findAllCount());
        return map;
    }

    //修改页面获取
    @GetMapping("/editExamType")
    public ModelAndView toEditExamType(Long id,Model model){
        System.out.println(id);
        DictExamType dictExamType = examTypeService.findById(id);
        model.addAttribute("DictExamType",dictExamType);
        return new ModelAndView("admin/examTypeForm.html","model",model);
    }
    //方法
    @PostMapping("/editExamType")
    @ResponseBody
    public String editExamType(DictExamType dictExamType){
        DictExamType examTypeNew = examTypeService.findById(dictExamType.getId());
        if (examTypeNew.getTypeName().equals(dictExamType.getTypeName())){
            examTypeNew.setTypeName(dictExamType.getTypeName());
            examTypeNew.setTypeCode(dictExamType.getTypeCode());
            examTypeNew.setTypeSortCode(dictExamType.getTypeSortCode());
            examTypeService.saveOne(examTypeNew);
            return "ok";
        }
        else {
            if (examTypeService.findOneByName(dictExamType.getTypeName()) != null) {
                return "equals";
            }
            else {
                examTypeNew.setTypeName(dictExamType.getTypeName());
                examTypeNew.setTypeCode(dictExamType.getTypeCode());
                examTypeNew.setTypeSortCode(dictExamType.getTypeSortCode());
                examTypeService.saveOne(examTypeNew);
                return "ok";
            }
        }
    }
    //添加学段的界面
    @GetMapping("/addExamType")
    public ModelAndView addExamType(Model model){
        return new ModelAndView("admin/examTypeAdd.html","model",model);
    }
    //添加班级的方法
    @PostMapping("/addExamType")
    @ResponseBody
    public String addExamType(DictExamType dictExamType){
        //判断班级名称是否存在
        if (examTypeService.findOneByName(dictExamType.getTypeName())!=null){
            return "equals";
        }
        dictExamType.setTypeName(dictExamType.getTypeName());
        dictExamType.setTypeCode(dictExamType.getTypeCode());
        dictExamType.setTypeSortCode(dictExamType.getTypeSortCode());
        try {
            examTypeService.saveOne(dictExamType);
            return "ok";
        }
        catch (Exception e){
            return "no";
        }
    }
    //删除单个
    @GetMapping("/deleteExamType")
    @ResponseBody
    public String deleteExamType(int id){
        DictExamType dictExamType = examTypeService.findById(Long.valueOf(id));
        int i =examTypeService.deleteExamType(id);
        System.out.println("======"+id);
        return "ok";
    }

    //多个删除
    @GetMapping("/deleteAllExamType")
    @ResponseBody
    public String deleteAllExamType(@RequestParam("id") String id){
        // 接收包含stuId的字符串，并将它分割成字符串数组
        String[] detList = id.split(",");
        // 将字符串数组转为List<Intger> 类型
        List<Long> LString = new ArrayList<Long>();
        for(String str : detList){
            DictExamType det = examTypeService.findById(Long.valueOf(id));
            /*if (det.getStudents()!=null&&det.getTeachers()!=null&&det.getCgClasses()!=null){
                return "no";
            }*/
            LString.add(new Long(str));
        }
        // 调用service层的批量删除函数
        examTypeService.deleteAllExamType(LString);
        return "ok";
    }


    //学科列表获取
    @GetMapping("/studySubject")
    @ResponseBody
    public Map<String,Object> studySubject(Integer page, Integer limit){
        page--;
        Pageable pageable = PageRequest.of(page,limit);
        List<DictStudySubject> content = studySubjectService.findAll(pageable).getContent();
        Map<String,Object> map = new HashMap<>();
        map.put("data",content);
        map.put("size",studySubjectService.findAllCount());
        return map;
    }

    //修改页面获取
    @GetMapping("/editStudySubject")
    public ModelAndView toStudySubject(Long id,Model model){

        DictStudySubject studySubject = studySubjectService.findById(id);
        model.addAttribute("studySubject",studySubject);
        return new ModelAndView("admin/studySubjectForm.html","model",model);
    }
    //方法
    @PostMapping("/editStudySubject")
    @ResponseBody
    public String editStudySubject(DictStudySubject studySubject){
        DictStudySubject studySubjectNew = studySubjectService.findById(studySubject.getId());
        if (studySubjectNew.getSubjectName().equals(studySubject.getSubjectName())){

            if (studySubjectNew.getSubjectCode().equals(studySubject.getSubjectCode())){
                if (studySubjectNew.getSubjectSortCode().equals(studySubject.getSubjectSortCode()))
                    return "noEdit";
            }

            studySubjectNew.setSubjectName(studySubject.getSubjectName());
            studySubjectNew.setSubjectCode(studySubject.getSubjectCode());
            studySubjectNew.setSubjectSortCode(studySubject.getSubjectSortCode());
            studySubjectService.saveOne(studySubjectNew);
            return "ok";
        }
        else {
            if (studySubjectService.findOneByName(studySubject.getSubjectName()) != null) {
                return "equals";
            }
            else {
                studySubjectNew.setSubjectName(studySubject.getSubjectName());
                studySubjectNew.setSubjectCode(studySubject.getSubjectCode());
                studySubjectNew.setSubjectSortCode(studySubject.getSubjectSortCode());
                studySubjectService.saveOne(studySubjectNew);
                return "ok";
            }
        }

    }

    //添加科目的界面
    @GetMapping("/addStudySubject")
    public ModelAndView addStudySubject(Model model){

        return new ModelAndView("admin/studySubjectAdd.html","model",model);
    }


    //添加科目的方法
    @PostMapping("/addStudySubject")
    @ResponseBody
    public String addStudySubject(DictStudySubject studySubject){
        if (studySubjectService.findOneByName(studySubject.getSubjectName())!=null){
            return "equals";
        }
        try {
            studySubjectService.saveOne(studySubject);
            return "ok";
        }
        catch (Exception e){
            return "no";
        }
    }

    //单个删除
    @GetMapping("/deleteStudySubject")
    @ResponseBody
    public String deleteStudySubject(int id){
        DictStudySubject dss = studySubjectService.findById(Long.valueOf(id));
        studySubjectService.deleteStudySubject(id);
        return "no";
    }
    //多个删除
    @GetMapping("/deleteAllStudySubject")
    @ResponseBody
    public String deleteAllStudySubject(@RequestParam("id") String id){
        // 接收包含stuId的字符串，并将它分割成字符串数组
        String[] dssList = id.split(",");
        // 将字符串数组转为List<Intger> 类型
        List<Long> LString = new ArrayList<Long>();
        for(String str : dssList){
            DictStudySubject dss = studySubjectService.findById(Long.valueOf(id));
         /*   if (dep.getStudents()!=null&&dep.getTeachers()!=null&&dep.getCgClasses()!=null){
                return "no";
            }*/
            LString.add(new Long(str));
        }
        // 调用service层的批量删除函数
        studySubjectService.deleteAllStudySubject(LString);
        return "ok";
    }


    //搜索科目
    @GetMapping("/LikeStudySubject")
    @ResponseBody
    public Map<String,Object> studySubject(Integer page, Integer limit,DictStudySubject studySubject){
        page--;
        Pageable pageable = PageRequest.of(page,limit);
        List<DictStudySubject> content = studySubjectService.findAll(new Specification<DictStudySubject>(){
            @Override
            public Predicate toPredicate(Root<DictStudySubject> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<>();
                //根据dptName 模糊查询Department
                if (StringUtils.isNotBlank(studySubject.getSubjectName())) {
                    list.add(cb.like(root.get("subjectName").as(String.class), "%" + studySubject.getSubjectName() + "%"));
                }
                Predicate[] pre = new Predicate[list.size()];
                criteriaQuery.where(list.toArray(pre));
                return cb.and(list.toArray(pre));
            }
        },pageable).getContent();
        Map<String,Object> map = new HashMap<>();
        map.put("data",content);
        map.put("size",studySubjectService.findAllCount());
        return map;
    }

    //缺席原因列表获取：页面
    @GetMapping("/confirmResult")
    @ResponseBody
    public Map<String,Object> confirmResult(Integer page, Integer limit){
        page--;
        Pageable pageable = PageRequest.of(page,limit);
        List<DictConfirmResult> content = confirmResultService.findAll(pageable).getContent();
        Map<String,Object> map = new HashMap<>();
        map.put("data",content);
        map.put("size", confirmResultService.findAllCount());
        return map;
    }
    //修改页面获取
    @GetMapping("/editConfirmResult")
    public ModelAndView toEditDepartment(Long id,Model model){

        DictConfirmResult confirmResult = confirmResultService.findById(id);
        model.addAttribute("confirmResult",confirmResult);
        return new ModelAndView("admin/confirmResultForm.html","model",model);
    }
    //方法
    @PostMapping("/editConfirmResult")
    @ResponseBody
    public String editDepartment(DictConfirmResult confirmResult){
        DictConfirmResult confirmResultNew = confirmResultService.findById(confirmResult.getId());
        if (confirmResultNew.getConfirmResultName().equals(confirmResult.getConfirmResultName())){

            if (confirmResultNew.getConfirmResultCode().equals(confirmResult.getConfirmResultCode())){
                    return "noEdit";
            }

            confirmResultNew.setConfirmResultName(confirmResult.getConfirmResultName());
            confirmResultNew.setConfirmResultCode(confirmResult.getConfirmResultCode());
            confirmResultService.saveOne(confirmResultNew);
            return "ok";
        }
        else {
            if (confirmResultService.findOneByName(confirmResult.getConfirmResultName()) != null) {
                return "equals";
            }
            else {
                confirmResultNew.setConfirmResultName(confirmResult.getConfirmResultName());
                confirmResultNew.setConfirmResultCode(confirmResult.getConfirmResultCode());
                confirmResultService.saveOne(confirmResultNew);
                return "ok";
            }
        }

    }

    //添加缺席原因的界面
    @GetMapping("/addConfirmResult")
    public ModelAndView addDepartment(Model model){

        return new ModelAndView("admin/confirmResultAdd.html","model",model);
    }


    //添加缺席原因的方法
    @PostMapping("/addConfirmResult")
    @ResponseBody
    public String addConfirmResult(DictConfirmResult confirmResult){
        if (confirmResultService.findOneByName(confirmResult.getConfirmResultName())!=null){
            return "equals";
        }
        try {
            confirmResultService.saveOne(confirmResult);
            return "ok";
        }
        catch (Exception e){
            return "no";
        }
    }

    //单个删除
    @GetMapping("/deleteConfirmResult")
    @ResponseBody
    public String deleteConfirmResult(int id){
        DictConfirmResult dcr = confirmResultService.findById(Long.valueOf(id));
        /*if (dcr.getStudents()!=null&&dcr.getTeachers()!=null&&dcr.getCgClasses()!=null){
            return "no";
        }*/
        confirmResultService.deleteConfirmResult(id);
        return "no";
    }
    //多个删除
    @GetMapping("/deleteAllConfirmResult")
    @ResponseBody
    public String deleteAllConfirmResult(@RequestParam("id") String id){
        // 接收包含stuId的字符串，并将它分割成字符串数组
        String[] dcrList = id.split(",");
        // 将字符串数组转为List<Intger> 类型
        List<Long> LString = new ArrayList<Long>();
        for(String str : dcrList){
            DictConfirmResult dep = confirmResultService.findById(Long.valueOf(id));
            /*if (dep.getStudents()!=null&&dep.getTeachers()!=null&&dep.getCgClasses()!=null){
                return "no";
            }*/
            LString.add(new Long(str));
        }
        // 调用service层的批量删除函数
        confirmResultService.deleteAllConfirmResult(LString);
        return "ok";
    }


    //搜索缺席原因
    @GetMapping("/LikeConfirmResult")
    @ResponseBody
    public Map<String,Object> department(Integer page, Integer limit,DictConfirmResult confirmResult){
        page--;
        Pageable pageable = PageRequest.of(page,limit);
        List<DictConfirmResult> content = confirmResultService.findAll(new Specification<DictConfirmResult>(){
            @Override
            public Predicate toPredicate(Root<DictConfirmResult> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<>();
                //根据dptName 模糊查询Department
                if (StringUtils.isNotBlank(confirmResult.getConfirmResultName())) {
                    list.add(cb.like(root.get("ConfirmResultName").as(String.class), "%" + confirmResult.getConfirmResultName() + "%"));
                }
                Predicate[] pre = new Predicate[list.size()];
                criteriaQuery.where(list.toArray(pre));
                return cb.and(list.toArray(pre));
            }
        },pageable).getContent();
        Map<String,Object> map = new HashMap<>();
        map.put("data",content);
        map.put("size", confirmResultService.findAllCount());
        return map;
    }

    //区县列表获取
    @GetMapping("/district")
    @ResponseBody
    public Map<String,Object> course(Integer page, Integer limit){
        page--;
        Pageable pageable = PageRequest.of(page,limit);
        List<DictDistrict> content = districtService.findAll(pageable).getContent();
        Map<String,Object> map = new HashMap<>();
        map.put("data",content);
        map.put("size",districtService.findAllCount());
        return map;
    }
    //修改区县列表页面
    @GetMapping("/editDistrict")
    public ModelAndView toEditDistrict(Long id,Model model){
        DictDistrict district = districtService.findById(id);
        model.addAttribute("district",district);
        return new ModelAndView("admin/districtForm.html","model",model);
    }
    //方法
    @PostMapping("/editDistrict")
    @ResponseBody
    public String editDistrict(DictDistrict district){


        DictDistrict districtNew = districtService.findById(district.getId());
        districtNew.setDistrictName(district.getDistrictName());
        districtNew.setDistrictCode(district.getDistrictCode());
        districtNew.setDistrictSortCode(district.getDistrictSortCode());
        districtService.saveOne(districtNew);
        return "ok";
    }
    //添加区县的界面
    @GetMapping("/addDistrict")
    public ModelAndView addDistrict(Model model){
        return new ModelAndView("admin/addDistrict.html","model",model);
    }

    //添加区县的方法
    @PostMapping("/addDistrict")
    @ResponseBody
    public String addDistrict(DictDistrict district){
        if (districtService.findOneByName(district.getDistrictName())!=null){
            return "equals";
        }
        try {
            districtService.saveOne(district);
            return "ok";
        }
        catch (Exception e){
            return "no";
        }
    }
    //删除单个
    @GetMapping("/deleteDistrict")
    @ResponseBody
    public int deleteDistrict(int id){
        int i =districtService.deleteDistrict(id);
        System.out.println("======"+id);
        return i;
    }
    //删除多个
    @GetMapping("/deleteAllDistrict")
    @ResponseBody
    public int deleteAllDistrict(@RequestParam("id") String id){
        // 接收包含stuId的字符串，并将它分割成字符串数组
        String[] disList = id.split(",");
        // 将字符串数组转为List<Intger> 类型
        List<Long> LString = new ArrayList<Long>();
        for(String str : disList){
            LString.add(new Long(str));
        }
        System.out.println("====="+LString);
        // 调用service层的批量删除函数
        int i = districtService.deleteAllDistrict(LString);
        return i;
    }


    //搜索区县
    @GetMapping("/LikeDistrict")
    @ResponseBody
    public Map<String,Object> course(Integer page, Integer limit,DictDistrict district){
        page--;
        Pageable pageable = PageRequest.of(page,limit);
        List<DictDistrict> content = districtService.findAll(new Specification<DictDistrict>(){
            @Override
            public Predicate toPredicate(Root<DictDistrict> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<>();
                //根据dptName 模糊查询Department
                if (StringUtils.isNotBlank(district.getDistrictName())) {
                    list.add(cb.like(root.get("districtName").as(String.class), "%" + district.getDistrictName() + "%"));
                }
                Predicate[] pre = new Predicate[list.size()];
                criteriaQuery.where(list.toArray(pre));
                return cb.and(list.toArray(pre));
            }
        },pageable).getContent();
        Map<String,Object> map = new HashMap<>();
        map.put("data",content);
        map.put("size",districtService.findAllCount());
        return map;
    }


    //考点列表获取
    @GetMapping("/examSite")
    @ResponseBody
    public Map<String,Object> examSite(Integer page, Integer limit){
        page--;
        Pageable pageable = PageRequest.of(page,limit);
        List<ExamSite> content = examSiteService.findAll(pageable).getContent();
        System.out.println(content);
        Map<String,Object> map = new HashMap<>();
        for (ExamSite s:content
        ) {
            s.setDistrictName(s.getDistrict().getDistrictName());
        }
        map.put("data",content);
        map.put("size",examSiteService.findAllCount());
        return map;
    }
    //修改考点信息：页面
    @GetMapping("/editExamSite")
    public ModelAndView editExamSite(Long id,Model model){
        ExamSite examSite = examSiteService.findById(id);
        List<DictDistrict> districtList = districtService.findAllList();
        model.addAttribute("district",examSite.getDistrict().getDistrictName());
        model.addAttribute("examSite",examSite);
        model.addAttribute("districtList",districtList);
        return new ModelAndView("admin/examSiteForm.html","model",model);
    }
    //修改考点方法
    @PostMapping("/editExamSite")
    @ResponseBody
    public String editExamSite(ExamSite examSite,Long district){
        ExamSite examSiteNew = examSiteService.findById(examSite.getId());


        if (examSiteNew.getExamSiteName().equals(examSite.getExamSiteName())) {
            if (examSiteNew.getPrincipal().equals(examSite.getPrincipal())){
                if (examSiteNew.getPrnTel().equals(examSite.getPrnTel())){
                        if (examSiteNew.getDistrict().getDistrictName().equals(examSite.getDistrict().getDistrictName())){
                            return "noEdit";
                        }
                }
            }


            examSiteNew.setExamSiteName(examSite.getExamSiteName());
            examSiteNew.setPrincipal(examSite.getPrincipal());
            examSiteNew.setPrnTel(examSite.getPrnTel());
            examSiteNew.setDistrict(districtService.findById(district));
            examSiteService.saveOne(examSiteNew);
            return "ok";
        } else {
            if (examSiteService.findOneByName(examSite.getExamSiteName()) != null) {
                return "equals";
            } else {
                examSiteNew.setExamSiteName(examSite.getExamSiteName());
                examSiteNew.setPrincipal(examSite.getPrincipal());
                examSiteNew.setPrnTel(examSite.getPrnTel());
                examSiteNew.setDistrict(districtService.findById(district));
                examSiteService.saveOne(examSiteNew);
                return "ok";
            }

        }
    }
    //添加考点的界面
    @GetMapping("/addExamSite")
    public ModelAndView addExamSite(Model model){

        List<DictDistrict> districtList = districtService.findAllList();
        model.addAttribute("districtList",districtList);


        return new ModelAndView("admin/examSiteAdd.html","model",model);
    }


    //添加考点的方法
    @PostMapping("/addExamSite")
    @ResponseBody
    public String addExamSite(ExamSite examSite ,Long district){
        //判断工号是否存在

        if (examSiteService.findOneByName(examSite.getExamSiteName())!=null){
            return "equals";
        }
        examSite.setExamSiteName(examSite.getExamSiteName());
        examSite.setPrincipal(examSite.getPrincipal());
        examSite.setPrnTel(examSite.getPrnTel());
        examSite.setDistrict(districtService.findById(district));
        try {
            examSiteService.saveOne(examSite);
            return "ok";
        }
        catch (Exception e){
            return "no";
        }


    }
    //删除单个考点
    @GetMapping("/deleteExamSite")
    @ResponseBody
    public int deleteExamSite(int id){
        int i =examSiteService.deleteExamSite(id);
        System.out.println("======"+id);
        return i;
    }
    //删除多个教师
    @GetMapping("/deleteAllExamSite")
    @ResponseBody
    public int deleteAllExamSite(@RequestParam("id") String id){
        // 接收包含stuId的字符串，并将它分割成字符串数组
        String[] exaList = id.split(",");
        // 将字符串数组转为List<Intger> 类型
        List<Long> LString = new ArrayList<Long>();
        for(String str : exaList){
            LString.add(new Long(str));
        }
        System.out.println("====="+LString);
        // 调用service层的批量删除函数
        int i = examSiteService.deleteAllExamSite(LString);
        return i;
    }



    //搜索老师
    @GetMapping("/LikeExamSite")
    @ResponseBody
    public Map<String,Object> examSite(Integer page, Integer limit,ExamSite examSite){
        page--;
        Pageable pageable = PageRequest.of(page,limit);
        List<ExamSite> content = examSiteService.findAll(new Specification<ExamSite>(){
            @Override
            public Predicate toPredicate(Root<ExamSite> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<>();
                //根据username 模糊查询ExamSite
                if (StringUtils.isNotBlank(examSite.getExamSiteName())) {
                    list.add(cb.like(root.get("examSiteName").as(String.class), "%" + examSite.getExamSiteName() + "%"));
                }
                //根据teacherNumber 查询ExamSite
                if (StringUtils.isNotBlank(examSite.getPrincipal())) {
                    list.add(cb.like(root.get("principal").as(String.class),"%" +  examSite.getPrincipal()+ "%"));
                }
                //根据dptName 查询teacher
                if (StringUtils.isNotBlank(examSite.getDistrictName())) {
                    Join<DictDistrict, ExamSite> join = root.join("district", JoinType.LEFT);
                    list.add(cb.like(join.get("districtName"),"%"+ examSite.getExamSiteName()+ "%"));
                }
                Predicate[] pre = new Predicate[list.size()];
                criteriaQuery.where(list.toArray(pre));
                return cb.and(list.toArray(pre));
            }
        },pageable).getContent();
        Map<String,Object> map = new HashMap<>();
        for (ExamSite t:content
        ) {
            t.setDistrictName(t.getDistrict().getDistrictName());
        }
        map.put("data",content);
        map.put("size",examSiteService.findAllCount());
        return map;
    }



}

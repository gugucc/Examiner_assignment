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
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.*;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class AdminController {
    @Autowired
    private LoginService loginService;
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
    private EvaluateItemService evaluateItemService;
    @Autowired
    private ExamSiteService examSiteService;
    @Autowired
    private ExamService examService;
    @Autowired
    private EvaluationRecordingService evaluationRecordingService;

    //管理员登录后主页面
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView index(@ModelAttribute("msg") String msg, Model model, HttpServletRequest request) {
        model.addAttribute("msg", msg);
        if (!(request.getSession().getAttribute("user") instanceof Admin)) {
            return new ModelAndView("error/403");
        }
        return new ModelAndView("admin/index");
    }

    //管理员修改账号（信息，密码等）
    @PostMapping(value = "/admin")
    @ResponseBody
    public String changePassword(String old_password, String new_password) {
        Admin admin = adminService.findOneByPassword(old_password);
        if (admin != null) {
            admin.setPassword(new_password);
            adminService.save(admin);
            return "ok";
        } else if (admin == null) {
            return "no";
        }
        return "fail";
    }

    //管理员注册
    @PostMapping(value = "/insertAdmin")
    @ResponseBody
    public String insertAdmin(Admin admin) {
        if (loginService.AdminLogin(admin.getUsername()) != null) {
            return "no";
        }else{
            String MD5Password= DigestUtils.md5DigestAsHex(admin.getPassword().getBytes());
            admin.setUsername(admin.getUsername());
            admin.setPassword(MD5Password);
            adminService.save(admin);
            return "ok";
        }
    }


    //考官列表获取
    @GetMapping("/examiner")
    @ResponseBody
    public Map<String, Object> examiner(Integer page, Integer limit) {
        page--;
        Pageable pageable = PageRequest.of(page, limit);
        List<Examiner> content = examinerService.findAll(pageable).getContent();
        Map<String, Object> map = new HashMap<>();
        map.put("data", content);
        map.put("size", examinerService.findAllCount());
        return map;
    }


    //修改考官信息：页面
    @GetMapping("/editExaminer")
    public ModelAndView editExaminerPage(Long id, Model model) {
        Examiner examiner = examinerService.findById(id);
        model.addAttribute("nation",examiner.getNation());
        model.addAttribute("politicalName",examiner.getPolitical());
        model.addAttribute("TypeName",examiner.getExamType());
        model.addAttribute("studySubject",examiner.getSubjectName());
        model.addAttribute("district",examiner.getDistrict());
        List<DictNation> nationList = nationService.findAllList();
        List<DictPolitical> politicalList = politicalService.findAllList();
        List<DictExamType> examTypeList = examTypeService.findAllList();
        List<DictStudySubject> studySubjectList = studySubjectService.findAllList();
        List<DictDistrict> districtList = districtService.findAllList();
        model.addAttribute("examiner", examiner);
        model.addAttribute("nationList", nationList);
        model.addAttribute("politicalList", politicalList);
        model.addAttribute("examTypeList", examTypeList);
        model.addAttribute("studySubjectList", studySubjectList);
        model.addAttribute("districtList", districtList);
        System.out.println(id);
        return new ModelAndView("admin/examinerForm.html", "model", model);
    }

    //方法
    @PostMapping("/editExaminer")
    @ResponseBody
    public String editExaminer(Examiner examiner) {

        System.out.println(examiner.getId());
        Examiner examinerNew = examinerService.findById(examiner.getId());
        /*DictNation nationNew = nationService.findById(nation);
        DictPolitical politicalNew = politicalService.findById(political);
        DictExamType examTypeNew = examTypeService.findById(examType);
        DictStudySubject studySubjectNew = studySubjectService.findById(studySubject);
        DictDistrict districtNew = districtService.findById(district);*/
        if (examinerNew.getExaminerName().equals(examiner.getExaminerName())) {
            if (examinerNew.getIdCard().equals(examiner.getIdCard())) {
                if (examinerNew.getSex().equals(examiner.getSex())) {
                    if (examinerNew.getNation().equals(examiner.getNation())) {
                        if (examinerNew.getPolitical().equals(examiner.getPolitical())) {
                            if (examinerNew.getExamType().equals(examiner.getExamType())) {
                                if (examinerNew.getSubjectName().equals(examiner.getSubjectName())) {
                                    if (examinerNew.getCompanyName().equals(examiner.getCompanyName())) {
                                        if (examinerNew.getPosition().equals(examiner.getPosition())) {
                                            if (examinerNew.getMailingAddress().equals(examiner.getMailingAddress())) {
                                                if (examinerNew.getFixedTel().equals(examiner.getFixedTel())) {
                                                    if (examinerNew.getTel().equals(examiner.getTel())) {
                                                        if (examinerNew.getEmail().equals(examiner.getEmail())) {
                                                            if (examinerNew.getDistrict().equals(examiner.getDistrict())) {
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
            examinerNew.setNation(examiner.getNation());
            examinerNew.setPolitical(examiner.getPolitical());
            examinerNew.setExamType(examiner.getExamType());
            examinerNew.setSubjectName(examiner.getSubjectName());
            examinerNew.setCompanyName(examiner.getCompanyName());
            examinerNew.setMailingAddress(examiner.getMailingAddress());
            examinerNew.setFixedTel(examiner.getFixedTel());
            examinerNew.setTel(examiner.getTel());
            examinerNew.setEmail(examiner.getEmail());
            examinerNew.setDistrict(examiner.getDistrict());
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
                examinerNew.setNation(examiner.getNation());
                examinerNew.setPolitical(examiner.getPolitical());
                examinerNew.setExamType(examiner.getExamType());
                examinerNew.setSubjectName(examiner.getSubjectName());
                examinerNew.setCompanyName(examiner.getCompanyName());
                examinerNew.setMailingAddress(examiner.getMailingAddress());
                examinerNew.setFixedTel(examiner.getFixedTel());
                examinerNew.setTel(examiner.getTel());
                examinerNew.setEmail(examiner.getEmail());
                examinerNew.setDistrict(examiner.getDistrict());
                examinerNew.setState(examiner.getState());
                examinerService.saveOne(examinerNew);
                return "ok";

            }
        }

    }


    //添加考官的界面
    @GetMapping("/addExaminer")
    public ModelAndView addExaminer(Model model) {
        List<DictNation> nationList = nationService.findAllList();
        List<DictPolitical> politicalList = politicalService.findAllList();
        List<DictExamType> examTypeList = examTypeService.findAllList();
        List<DictStudySubject> studySubjectList = studySubjectService.findAllList();
        List<DictDistrict> districtList = districtService.findAllList();
        model.addAttribute("nationList", nationList);
        model.addAttribute("politicalList", politicalList);
        model.addAttribute("examTypeList", examTypeList);
        model.addAttribute("studySubjectList", studySubjectList);
        model.addAttribute("districtList", districtList);
        // model.addAttribute("cgClassList",cgClassList);

        return new ModelAndView("admin/examinerAdd.html", "model", model);
    }

    //添加考官的方法
    @PostMapping("/addExaminer")
    @ResponseBody
    public String addExaminer(Examiner examiner/*,Long nationId,Long politicalId,Long examTypeId,Long studySubjectId,Long districtId*/) {
        /*DictNation nation = nationService.findById(nationId);
        DictPolitical political = politicalService.findById(politicalId);
        DictExamType examType = examTypeService.findById(examTypeId);
        DictStudySubject studySubject = studySubjectService.findById(studySubjectId);
        DictDistrict district = districtService.findById(districtId);*/
        //判断身份证号是否存在
        if (examinerService.findOneByIdCard(examiner.getIdCard()) != null) {
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
        System.out.println(examiner.getState());
        System.out.println(examiner.getSubjectName());
        try {
            examinerService.saveOne(examiner);
            return "ok";
        } catch (Exception e) {
            return "no";
        }

    }


    //删除单个考官
    @PostMapping("/deleteExaminer")
    @ResponseBody
    public int deleteExaminer(int id) {
        int i = examinerService.deleteExaminer(id);
        System.out.println("======" + id);
        return i;
    }

    //删除多个学考官
    @PostMapping("/deleteAllExaminer")
    @ResponseBody
    public int deleteAllExaminer(@RequestParam("id") String id) {
        // 接收包含stuId的字符串，并将它分割成字符串数组
        String[] stuList = id.split(",");
        // 将字符串数组转为List<Intger> 类型
        List<Long> LString = new ArrayList<Long>();
        for (String str : stuList) {
            LString.add(new Long(str));
        }
        System.out.println("=====" + LString);
        // 调用service层的批量删除函数
        int i = examinerService.deleteAllExaminer(LString);
        return i;
    }


    //搜索考官
    @GetMapping("/LikeExaminer")
    @ResponseBody
    public Map<String, Object> examiner(Integer page, Integer limit, Examiner examiner) {
        page--;
        Pageable pageable = PageRequest.of(page, limit);
        List<Examiner> content = examinerService.findAll(new Specification<Examiner>() {
            @Override
            public Predicate toPredicate(Root<Examiner> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<>();
                //根据examinerName 模糊查询 examiner
                if (StringUtils.isNotBlank(examiner.getExaminerName())) {
                    list.add(cb.like(root.get("examinerName").as(String.class), "%" + examiner.getExaminerName() + "%"));
                }
                //根据subjectCode 查询 examiner
                if (StringUtils.isNotBlank(examiner.getSubjectName())) {
                    list.add(cb.like(root.get("subjectCode").as(String.class), "%" + examiner.getSubjectName() + "%"));
                }
                //根据examType 查询 examiner
                if (StringUtils.isNotBlank(examiner.getExamType())) {
                    list.add(cb.like(root.get("examType").as(String.class), "%" + examiner.getExamType() + "%"));
                }
                //根据idCard 查询 examiner
                if (StringUtils.isNotBlank(examiner.getIdCard())) {
                    list.add(cb.like(root.get("idCard").as(String.class), "%" + examiner.getIdCard() + "%"));
                }
                //根据examinerDistrict 查询 examiner
                if (StringUtils.isNotBlank(examiner.getDistrict())) {
                    list.add(cb.like(root.get("district").as(String.class), "%" + examiner.getDistrict() + "%"));
                }
                Predicate[] pre = new Predicate[list.size()];
                criteriaQuery.where(list.toArray(pre));
                return cb.and(list.toArray(pre));
            }
        }, pageable).getContent();
        Map<String, Object> map = new HashMap<>();

        map.put("data", content);
        map.put("size", examinerService.findAllCount());
        return map;
    }


    //学段列表获取
    @GetMapping("/examType")
    @ResponseBody
    public Map<String, Object> examType(Integer page, Integer limit) {
        page--;
        Pageable pageable = PageRequest.of(page, limit);
        List<DictExamType> content = examTypeService.findAll(pageable).getContent();
        Map<String, Object> map = new HashMap<>();
        map.put("data", content);
        map.put("size", examTypeService.findAllCount());
        return map;
    }

    //修改页面获取
    @GetMapping("/editExamType")
    public ModelAndView toEditExamType(Long id, Model model) {
        System.out.println(id);
        DictExamType dictExamType = examTypeService.findById(id);
        model.addAttribute("dictExamType", dictExamType);
        return new ModelAndView("admin/examTypeForm.html", "model", model);
    }

    //方法
    @PostMapping("/editExamType")
    @ResponseBody
    public String editExamType(DictExamType dictExamType) {
        DictExamType examTypeNew = examTypeService.findById(dictExamType.getId());
        if (examTypeNew.getTypeName().equals(dictExamType.getTypeName())) {
            examTypeNew.setTypeName(dictExamType.getTypeName());
            examTypeNew.setTypeCode(dictExamType.getTypeCode());
            examTypeNew.setTypeSortCode(dictExamType.getTypeSortCode());
            examTypeService.saveOne(examTypeNew);
            return "ok";
        } else {
            if (examTypeService.findOneByName(dictExamType.getTypeName()) != null) {
                return "equals";
            } else {
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
    public ModelAndView addExamType(Model model) {
        return new ModelAndView("admin/examTypeAdd.html", "model", model);
    }

    //添加学段的方法
    @PostMapping("/addExamType")
    @ResponseBody
    public String addExamType(DictExamType dictExamType) {
        //判断班级名称是否存在
        if (examTypeService.findOneByName(dictExamType.getTypeName()) != null) {
            return "equals";
        }
        dictExamType.setTypeName(dictExamType.getTypeName());
        dictExamType.setTypeCode(dictExamType.getTypeCode());
        dictExamType.setTypeSortCode(dictExamType.getTypeSortCode());
        try {
            examTypeService.saveOne(dictExamType);
            return "ok";
        } catch (Exception e) {
            return "no";
        }
    }

    //删除单个
    @GetMapping("/deleteExamType")
    @ResponseBody
    public String deleteExamType(int id) {
        DictExamType dictExamType = examTypeService.findById(Long.valueOf(id));
        int i = examTypeService.deleteExamType(id);
        System.out.println("======" + id);
        return "ok";
    }

    //多个删除
    @GetMapping("/deleteAllExamType")
    @ResponseBody
    public String deleteAllExamType(@RequestParam("id") String id) {
        // 接收包含stuId的字符串，并将它分割成字符串数组
        String[] detList = id.split(",");
        // 将字符串数组转为List<Intger> 类型
        List<Long> LString = new ArrayList<Long>();
        for (String str : detList) {
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
    //搜索学段
    @GetMapping("/LikeExamType")
    @ResponseBody
    public Map<String, Object> LikeExamType(Integer page, Integer limit, DictExamType dictExamType) {
        page--;
        Pageable pageable = PageRequest.of(page, limit);
        List<DictExamType> content = examTypeService.findAll(new Specification<DictExamType>() {
            @Override
            public Predicate toPredicate(Root<DictExamType> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<>();
                //根据typeName 模糊查询ExamType
                if (StringUtils.isNotBlank(dictExamType.getTypeName())) {
                    list.add(cb.like(root.get("typeName").as(String.class), "%" + dictExamType.getTypeName() + "%"));
                }
                if (StringUtils.isNotBlank(dictExamType.getTypeCode())) {
                    list.add(cb.like(root.get("typeCode").as(String.class), "%" + dictExamType.getTypeCode() + "%"));
                }
                Predicate[] pre = new Predicate[list.size()];
                criteriaQuery.where(list.toArray(pre));
                return cb.and(list.toArray(pre));
            }
        }, pageable).getContent();
        Map<String, Object> map = new HashMap<>();
        map.put("data", content);
        map.put("size", examTypeService.findAllCount());
        return map;
    }


    //学科列表获取
    @GetMapping("/studySubject")
    @ResponseBody
    public Map<String, Object> studySubject(Integer page, Integer limit) {
        page--;
        Pageable pageable = PageRequest.of(page, limit);
        List<DictStudySubject> content = studySubjectService.findAll(pageable).getContent();
        Map<String, Object> map = new HashMap<>();
        map.put("data", content);
        map.put("size", studySubjectService.findAllCount());
        return map;
    }

    //修改页面获取
    @GetMapping("/editStudySubject")
    public ModelAndView toStudySubject(Long id, Model model) {

        DictStudySubject studySubject = studySubjectService.findById(id);
        model.addAttribute("studySubject", studySubject);
        return new ModelAndView("admin/studySubjectForm.html", "model", model);
    }

    //方法
    @PostMapping("/editStudySubject")
    @ResponseBody
    public String editStudySubject(DictStudySubject studySubject) {
        DictStudySubject studySubjectNew = studySubjectService.findById(studySubject.getId());
        if (studySubjectNew.getSubjectName().equals(studySubject.getSubjectName())) {

            if (studySubjectNew.getSubjectCode().equals(studySubject.getSubjectCode())) {
                if (studySubjectNew.getSubjectSortCode().equals(studySubject.getSubjectSortCode()))
                    return "noEdit";
            }

            studySubjectNew.setSubjectName(studySubject.getSubjectName());
            studySubjectNew.setSubjectCode(studySubject.getSubjectCode());
            studySubjectNew.setSubjectSortCode(studySubject.getSubjectSortCode());
            studySubjectService.saveOne(studySubjectNew);
            return "ok";
        } else {
            if (studySubjectService.findOneByName(studySubject.getSubjectName()) != null) {
                return "equals";
            } else {
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
    public ModelAndView addStudySubject(Model model) {

        return new ModelAndView("admin/studySubjectAdd.html", "model", model);
    }


    //添加科目的方法
    @PostMapping("/addStudySubject")
    @ResponseBody
    public String addStudySubject(DictStudySubject studySubject) {
        if (studySubjectService.findOneByName(studySubject.getSubjectName()) != null) {
            return "equals";
        }
        try {
            studySubjectService.saveOne(studySubject);
            return "ok";
        } catch (Exception e) {
            return "no";
        }
    }

    //单个删除
    @GetMapping("/deleteStudySubject")
    @ResponseBody
    public String deleteStudySubject(int id) {
        DictStudySubject dss = studySubjectService.findById(Long.valueOf(id));
        studySubjectService.deleteStudySubject(id);
        return "no";
    }

    //多个删除
    @GetMapping("/deleteAllStudySubject")
    @ResponseBody
    public String deleteAllStudySubject(@RequestParam("id") String id) {
        // 接收包含stuId的字符串，并将它分割成字符串数组
        String[] dssList = id.split(",");
        // 将字符串数组转为List<Intger> 类型
        List<Long> LString = new ArrayList<Long>();
        for (String str : dssList) {
            DictStudySubject dss = studySubjectService.findById(Long.valueOf(id));
            LString.add(new Long(str));
        }
        // 调用service层的批量删除函数
        studySubjectService.deleteAllStudySubject(LString);
        return "ok";
    }


    //搜索科目
    @GetMapping("/LikeStudySubject")
    @ResponseBody
    public Map<String, Object> LikeStudySubject(Integer page, Integer limit, DictStudySubject studySubject) {
        page--;
        Pageable pageable = PageRequest.of(page, limit);
        List<DictStudySubject> content = studySubjectService.findAll(new Specification<DictStudySubject>() {
            @Override
            public Predicate toPredicate(Root<DictStudySubject> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<>();
                if (StringUtils.isNotBlank(studySubject.getSubjectName())) {
                    list.add(cb.like(root.get("subjectName").as(String.class), "%" + studySubject.getSubjectName() + "%"));
                }
                if (StringUtils.isNotBlank(studySubject.getSubjectCode())) {
                    list.add(cb.like(root.get("subjectCode").as(String.class), "%" + studySubject.getSubjectCode() + "%"));
                }
                Predicate[] pre = new Predicate[list.size()];
                criteriaQuery.where(list.toArray(pre));
                return cb.and(list.toArray(pre));
            }
        }, pageable).getContent();
        Map<String, Object> map = new HashMap<>();
        map.put("data", content);
        map.put("size", studySubjectService.findAllCount());
        return map;
    }

    //评价选项
    @GetMapping("/evaluateItem")
    @ResponseBody
    public Map<String, Object> evaluateItem(Integer page, Integer limit) {
        page--;
        Pageable pageable = PageRequest.of(page, limit);
        List<EvaluateItem> content = evaluateItemService.findAll(pageable).getContent();
        Map<String, Object> map = new HashMap<>();
        map.put("data", content);
        map.put("size", evaluateItemService.findAllCount());
        return map;
    }

    //修改评价选项
    @GetMapping("/editEvaluateItem")
    public ModelAndView toEditEvaluateItem(Long id, Model model) {

        EvaluateItem evaluateItem = evaluateItemService.findById(id);
        model.addAttribute("evaluateItem", evaluateItem);
        return new ModelAndView("admin/evaluateItemForm.html", "model", model);
    }

    //方法修改评价选项
    @PostMapping("/editEvaluateItem")
    @ResponseBody
    public String editEvaluateItem(EvaluateItem evaluateItem) {
        EvaluateItem evaluateItemNew = evaluateItemService.findById(evaluateItem.getId());
        if (evaluateItemNew.getEvaluateName().equals(evaluateItem.getEvaluateName())) {
            if (evaluateItemNew.getOptionA().equals(evaluateItem.getOptionA())) {
                if (evaluateItemNew.getOptionB().equals(evaluateItem.getOptionB())) {
                    if (evaluateItemNew.getOptionC().equals(evaluateItem.getOptionC())) {
                        return "noEdit";
                    }
                }
            }
            evaluateItemNew.setEvaluateName(evaluateItem.getEvaluateName());
            evaluateItemNew.setOptionA(evaluateItem.getOptionA());
            evaluateItemNew.setOptionB(evaluateItem.getOptionB());
            evaluateItemNew.setOptionC(evaluateItem.getOptionC());
            evaluateItemService.saveOne(evaluateItemNew);
            return "ok";
        } else {
            if (evaluateItemService.findOneByName(evaluateItem.getEvaluateName()) == null) {
                return "equals";
            } else {
                evaluateItemNew.setEvaluateName(evaluateItem.getEvaluateName());
                evaluateItemNew.setOptionA(evaluateItem.getOptionA());
                evaluateItemNew.setOptionB(evaluateItem.getOptionB());
                evaluateItemNew.setOptionC(evaluateItem.getOptionC());
                evaluateItemService.saveOne(evaluateItemNew);
                return "ok";
            }
        }

    }

    //添加评价选项
    @GetMapping("/addEvaluateItem")
    public ModelAndView addEvaluateItem(Model model) {
        return new ModelAndView("admin/evaluateItemAdd.html", "model", model);
    }


    //添加评价选项的方法
    @PostMapping("/addEvaluateItem")
    @ResponseBody
    public String addEvaluateItem(EvaluateItem evaluateItem) {
        if (evaluateItemService.findOneByName(evaluateItem.getEvaluateName()) != null) {
            return "equals";
        }
        try {
            evaluateItemService.saveOne(evaluateItem);
            return "ok";
        } catch (Exception e) {
            return "no";
        }
    }

    //单个删除评价选项
    @GetMapping("/deleteEvaluateItem")
    @ResponseBody
    public String deleteEvaluateItem(int id) {
        EvaluateItem ei = evaluateItemService.findById(Long.valueOf(id));
        /*if (dcr.getStudents()!=null&&dcr.getTeachers()!=null&&dcr.getCgClasses()!=null){
            return "no";
        }*/
        evaluateItemService.deleteEvaluateItem(id);
        return "no";
    }

    //多个删除评价选项
    @GetMapping("/deleteAllEvaluateItem")
    @ResponseBody
    public String deleteAllEvaluateItem(@RequestParam("id") String id) {
        // 接收包含stuId的字符串，并将它分割成字符串数组
        String[] dcrList = id.split(",");
        // 将字符串数组转为List<Intger> 类型
        List<Long> LString = new ArrayList<Long>();
        for (String str : dcrList) {
            EvaluateItem ei = evaluateItemService.findById(Long.valueOf(id));
            /*if (dep.getStudents()!=null&&dep.getTeachers()!=null&&dep.getCgClasses()!=null){
                return "no";
            }*/
            LString.add(new Long(str));
        }
        // 调用service层的批量删除函数
        evaluateItemService.deleteAllEvaluateItem(LString);
        return "ok";
    }


    //搜索评价选项
    @GetMapping("/LikeEvaluateItem")
    @ResponseBody
    public Map<String, Object> department(Integer page, Integer limit, EvaluateItem evaluateItem) {
        page--;
        Pageable pageable = PageRequest.of(page, limit);
        List<EvaluateItem> content = evaluateItemService.findAll(new Specification<EvaluateItem>() {
            @Override
            public Predicate toPredicate(Root<EvaluateItem> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<>();
                //根据dptName 模糊查询Department
                if (StringUtils.isNotBlank(evaluateItem.getEvaluateName())) {
                    list.add(cb.like(root.get("evaluateName").as(String.class), "%" + evaluateItem.getEvaluateName() + "%"));
                }
                Predicate[] pre = new Predicate[list.size()];
                criteriaQuery.where(list.toArray(pre));
                return cb.and(list.toArray(pre));
            }
        }, pageable).getContent();
        Map<String, Object> map = new HashMap<>();
        map.put("data", content);
        map.put("size", evaluateItemService.findAllCount());
        return map;
    }

    //区县列表获取
    @GetMapping("/district")
    @ResponseBody
    public Map<String, Object> course(Integer page, Integer limit) {
        page--;
        Pageable pageable = PageRequest.of(page, limit);
        List<DictDistrict> content = districtService.findAll(pageable).getContent();
        Map<String, Object> map = new HashMap<>();
        map.put("data", content);
        map.put("size", districtService.findAllCount());
        return map;
    }

    //修改区县列表页面
    @GetMapping("/editDistrict")
    public ModelAndView toEditDistrict(Long id, Model model) {
        DictDistrict district = districtService.findById(id);
        model.addAttribute("district", district);
        return new ModelAndView("admin/districtForm.html", "model", model);
    }

    //方法
    @PostMapping("/editDistrict")
    @ResponseBody
    public String editDistrict(DictDistrict district) {
        DictDistrict districtNew = districtService.findById(district.getId());
        districtNew.setDistrictName(district.getDistrictName());
        districtNew.setDistrictCode(district.getDistrictCode());
        districtNew.setDistrictSortCode(district.getDistrictSortCode());
        districtService.saveOne(districtNew);
        return "ok";
    }

    //添加区县的界面
    @GetMapping("/addDistrict")
    public ModelAndView addDistrict(Model model) {
        return new ModelAndView("admin/addDistrict.html", "model", model);
    }

    //添加区县的方法
    @PostMapping("/addDistrict")
    @ResponseBody
    public String addDistrict(DictDistrict district) {
        if (districtService.findOneByName(district.getDistrictName()) != null) {
            return "equals";
        }
        try {
            districtService.saveOne(district);
            return "ok";
        } catch (Exception e) {
            return "no";
        }
    }

    //删除单个
    @GetMapping("/deleteDistrict")
    @ResponseBody
    public int deleteDistrict(int id) {
        int i = districtService.deleteDistrict(id);
        System.out.println("======" + id);
        return i;
    }

    //删除多个
    @GetMapping("/deleteAllDistrict")
    @ResponseBody
    public int deleteAllDistrict(@RequestParam("id") String id) {
        // 接收包含stuId的字符串，并将它分割成字符串数组
        String[] disList = id.split(",");
        // 将字符串数组转为List<Intger> 类型
        List<Long> LString = new ArrayList<Long>();
        for (String str : disList) {
            LString.add(new Long(str));
        }
        System.out.println("=====" + LString);
        // 调用service层的批量删除函数
        int i = districtService.deleteAllDistrict(LString);
        return i;
    }


    //搜索区县
    @GetMapping("/LikeDistrict")
    @ResponseBody
    public Map<String, Object> course(Integer page, Integer limit, DictDistrict district) {
        page--;
        Pageable pageable = PageRequest.of(page, limit);
        List<DictDistrict> content = districtService.findAll(new Specification<DictDistrict>() {
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
        }, pageable).getContent();
        Map<String, Object> map = new HashMap<>();
        map.put("data", content);
        map.put("size", districtService.findAllCount());
        return map;
    }


    //考点列表获取
    @GetMapping("/examSite")
    @ResponseBody
    public Map<String, Object> examSite(Integer page, Integer limit) {
        page--;
        Pageable pageable = PageRequest.of(page, limit);
        List<ExamSite> content = examSiteService.findAll(pageable).getContent();
        System.out.println(content);
        Map<String, Object> map = new HashMap<>();
        /*for (ExamSite s:content
        ) {
            s.setDistrictName(s.getDistrict().getDistrictName());
        }*/
        map.put("data", content);
        map.put("size", examSiteService.findAllCount());
        return map;
    }

    //修改考点信息：页面
    @GetMapping("/editExamSite")
    public ModelAndView editExamSite(Long id, Model model) {
        ExamSite examSite = examSiteService.findById(id);
        List<DictDistrict> districtList = districtService.findAllList();
        model.addAttribute("district", examSite.getDistrict());
        model.addAttribute("examSite", examSite);
        model.addAttribute("districtList", districtList);
        return new ModelAndView("admin/examSiteForm.html", "model", model);
    }

    //修改考点方法
    @PostMapping("/editExamSite")
    @ResponseBody
    public String editExamSite(ExamSite examSite) {
        ExamSite examSiteNew = examSiteService.findById(examSite.getId());
        if (examSiteNew.getExamSiteName().equals(examSite.getExamSiteName())) {
            if (examSiteNew.getPrincipal().equals(examSite.getPrincipal())) {
                if (examSiteNew.getPrnTel().equals(examSite.getPrnTel())) {
                    if (examSiteNew.getDistrict().equals(examSite.getDistrict())) {
                        return "noEdit";
                    }
                }
            }
            examSiteNew.setExamSiteName(examSite.getExamSiteName());
            examSiteNew.setPrincipal(examSite.getPrincipal());
            examSiteNew.setPrnTel(examSite.getPrnTel());
            examSiteNew.setDistrict(examSite.getDistrict());
            examSiteService.saveOne(examSiteNew);
            return "ok";
        } else {
            if (examSiteService.findOneByName(examSite.getExamSiteName()) != null) {
                return "equals";
            } else {
                examSiteNew.setExamSiteName(examSite.getExamSiteName());
                examSiteNew.setPrincipal(examSite.getPrincipal());
                examSiteNew.setPrnTel(examSite.getPrnTel());
                examSiteNew.setDistrict(examSite.getDistrict());
                examSiteService.saveOne(examSiteNew);
                return "ok";
            }

        }
    }

    //添加考点的界面
    @GetMapping("/addExamSite")
    public ModelAndView addExamSite(Model model) {

        List<DictDistrict> districtList = districtService.findAllList();
        model.addAttribute("districtList", districtList);


        return new ModelAndView("admin/examSiteAdd.html", "model", model);
    }


    //添加考点的方法
    @PostMapping("/addExamSite")
    @ResponseBody
    public String addExamSite(ExamSite examSite) {
        //判断工号是否存在
        if (examSiteService.findOneByName(examSite.getExamSiteName()) != null) {
            return "equals";
        }
        examSite.setExamSiteName(examSite.getExamSiteName());
        examSite.setPrincipal(examSite.getPrincipal());
        examSite.setPrnTel(examSite.getPrnTel());
        examSite.setDistrict(examSite.getDistrict());
        try {
            examSiteService.saveOne(examSite);
            return "ok";
        } catch (Exception e) {
            return "no";
        }


    }

    //删除单个考点
    @GetMapping("/deleteExamSite")
    @ResponseBody
    public int deleteExamSite(int id) {
        int i = examSiteService.deleteExamSite(id);
        System.out.println("======" + id);
        return i;
    }

    //删除多个考点
    @GetMapping("/deleteAllExamSite")
    @ResponseBody
    public int deleteAllExamSite(@RequestParam("id") String id) {
        // 接收包含stuId的字符串，并将它分割成字符串数组
        String[] exaList = id.split(",");
        // 将字符串数组转为List<Intger> 类型
        List<Long> LString = new ArrayList<Long>();
        for (String str : exaList) {
            LString.add(new Long(str));
        }
        System.out.println("=====" + LString);
        // 调用service层的批量删除函数
        int i = examSiteService.deleteAllExamSite(LString);
        return i;
    }

    //搜索考点
    @GetMapping("/LikeExamSite")
    @ResponseBody
    public Map<String, Object> LikeExamSite(Integer page, Integer limit, ExamSite examSite) {
        page--;
        Pageable pageable = PageRequest.of(page, limit);
        List<ExamSite> content = examSiteService.findAll(new Specification<ExamSite>() {
            @Override
            public Predicate toPredicate(Root<ExamSite> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<>();
                if (StringUtils.isNotBlank(examSite.getDistrict())) {
                    list.add(cb.like(root.get("district").as(String.class), "%" + examSite.getDistrict() + "%"));
                }
                Predicate[] pre = new Predicate[list.size()];
                criteriaQuery.where(list.toArray(pre));
                return cb.and(list.toArray(pre));
            }
        }, pageable).getContent();
        Map<String, Object> map = new HashMap<>();
        map.put("data", content);
        map.put("size", examSiteService.findAllCount());
        return map;
    }

    //考试列表获取
    @GetMapping("/exam")
    @ResponseBody
    public Map<String, Object> exam(Integer page, Integer limit) {
        page--;
        //获取当前时间
        Date date = new Date();
        Pageable pageable = PageRequest.of(page, limit);
        List<Exam> content = examService.findAll(pageable).getContent();
        Map<String, Object> map = new HashMap<>();
        for (Exam s : content
        ) {
            s.setExamSiteName(s.getExamSite().getExamSiteName());
            Timestamp starTime=s.getStarTime();
            Timestamp endTime=s.getEndTime();
            Timestamp nowTime = new Timestamp(date.getTime());
            if (starTime.before(nowTime)) {//starTime时间比nowTime时间早,考试开始
                if (endTime.before(nowTime)){//true:考试结束
                    examService.updateState(4, s.getId());//state:4代表考试结束
                    examinerService.updateState("正常",s.getId());//修改考官状态为正常
                    System.out.println("结束时间"+endTime);
                    System.out.println("现在"+nowTime);
                }else {
                    examService.updateState(3, s.getId());//state:3代表正在考试
                    examinerService.updateState("正在监考",s.getId());//修改考官状态为正在监考
                    System.out.println("开始时间"+starTime);
                    System.out.println("现在"+nowTime);
                    System.out.println("结束时间"+endTime);
                }
            }
        }
        map.put("data", content);
        map.put("size", examService.findAllCount());
        return map;
    }

    //修改考试列表页面
    @GetMapping("/editExam")
    public ModelAndView toeditExam(Long id, Model model) {
        List<ExamSite> examSiteList = examSiteService.findAllList();
        List<DictStudySubject> studySubjectList = studySubjectService.findAllList();
        List<DictExamType> examTypeList = examTypeService.findAllList();
        Exam exam = examService.findById(id);
        model.addAttribute("studySubject",exam.getStudySubject());
        model.addAttribute("examSite",exam.getExamSite().getExamSiteName());
        model.addAttribute("examType",exam.getExamType());
        model.addAttribute("exam", exam);
        model.addAttribute("examSiteList", examSiteList);
        model.addAttribute("studySubjectList", studySubjectList);
        model.addAttribute("examTypeList", examTypeList);
        return new ModelAndView("admin/examForm.html", "model", model);
    }

    //修改考试方法
    @PostMapping("/editExam")
    @ResponseBody
    public String editExam(Exam exam, Long examSite) {
        Exam examNew = examService.findById(exam.getId());
        if (examNew.getExamName().equals(exam.getExamName())) {
            if (examNew.getExamCode().equals(exam.getExamCode())) {
                if (examNew.getStudySubject().equals(exam.getStudySubject())) {
                    if (examNew.getExamType().equals(exam.getExamType())) {
                        if (examNew.getStarTime().equals(exam.getStarTime())) {
                            if (examNew.getEndTime().equals(exam.getEndTime())) {
                                if (examNew.getExamSite().getExamSiteName().equals(exam.getExamSite().getExamSiteName())) {
                                    return "noEdit";
                                }
                            }
                        }
                    }
                }
            }
            examNew.setExamName(exam.getExamName());
            examNew.setExamCode(exam.getExamCode());
            examNew.setStudySubject(exam.getStudySubject());
            examNew.setExamType(exam.getExamType());
            examNew.setStarTime(exam.getStarTime());
            examNew.setEndTime(exam.getEndTime());
            examNew.setExamSite(examSiteService.findById(examSite));
            examService.saveOne(examNew);
            return "ok";
        } else {
            if (examSiteService.findOneByName(exam.getExamSiteName()) != null) {
                return "equals";
            } else {
                examNew.setExamName(exam.getExamName());
                examNew.setExamCode(exam.getExamCode());
                examNew.setStudySubject(exam.getStudySubject());
                examNew.setExamType(exam.getExamType());
                examNew.setStarTime(exam.getStarTime());
                examNew.setEndTime(exam.getEndTime());
                examNew.setExamSite(examSiteService.findById(examSite));
                examService.saveOne(examNew);
                return "ok";
            }

        }
    }

    //添加考试的界面
    @GetMapping("/addExam")
    public ModelAndView addExam(Model model) {
        List<ExamSite> examSiteList = examSiteService.findAllList();
        List<DictStudySubject> studySubjectList = studySubjectService.findAllList();
        List<DictExamType> examTypeList = examTypeService.findAllList();
        model.addAttribute("examSiteList", examSiteList);
        model.addAttribute("studySubjectList", studySubjectList);
        model.addAttribute("examTypeList", examTypeList);
        return new ModelAndView("admin/examAdd.html", "model", model);
    }

    //添加考试的方法
    @PostMapping("/addExam")
    @ResponseBody
    public String addExam(Exam exam) {
        //判断工号是否存在
        if (examService.findOneByName(exam.getExamName()) != null) {
            return "equals";
        }
        /*exam.setExamName(exam.getExamName());
        exam.setExamCode(exam.getExamCode());
        exam.setStarTime(exam.getEndTime());
        exam.setEndTime(exam.getEndTime());
        exam.setExamSite(examSiteService.findById(examSite));*/
        try {
            examService.saveOne(exam);
            return "ok";
        } catch (Exception e) {
            return "no";
        }
    }

    //删除单个
    @GetMapping("/deleteExam")
    @ResponseBody
    public int deleteExam(int id) {
        int i = examService.deleteExam(id);
        System.out.println("======" + id);
        return i;
    }

    //删除多个
    @GetMapping("/deleteAllExam")
    @ResponseBody
    public int deleteAllExam(@RequestParam("id") String id) {
        // 接收包含stuId的字符串，并将它分割成字符串数组
        String[] exaList = id.split(",");
        // 将字符串数组转为List<Intger> 类型
        List<Long> LString = new ArrayList<Long>();
        for (String str : exaList) {
            LString.add(new Long(str));
        }
        System.out.println("=====" + LString);
        // 调用service层的批量删除函数
        int i = examService.deleteAllExam(LString);
        return i;
    }


    //搜索考试
    @GetMapping("/LikeExam")
    @ResponseBody
    public Map<String, Object> exam(Integer page, Integer limit, Exam exam) {
        page--;
        Pageable pageable = PageRequest.of(page, limit);
        List<Exam> content = examService.findAll(new Specification<Exam>() {
            @Override
            public Predicate toPredicate(Root<Exam> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<>();
                //根据dptName 模糊查询Department
                if (StringUtils.isNotBlank(exam.getExamName())) {
                    list.add(cb.like(root.get("examName").as(String.class), "%" + exam.getExamName() + "%"));
                }
                if (StringUtils.isNotBlank(exam.getExamCode())) {
                    list.add(cb.like(root.get("examCode").as(String.class), "%" + exam.getExamCode() + "%"));
                }
                if (StringUtils.isNotBlank(exam.getStudySubject())) {
                    list.add(cb.like(root.get("studySubject").as(String.class), "%" + exam.getStudySubject() + "%"));
                }
                if (StringUtils.isNotBlank(exam.getExamType())) {
                    list.add(cb.like(root.get("examType").as(String.class), "%" + exam.getExamType() + "%"));
                }
                if (StringUtils.isNotBlank(exam.getExamSiteName())) {
                    list.add(cb.like(root.get("ExamSiteName").as(String.class), "%" + exam.getExamSiteName() + "%"));
                }
                Predicate[] pre = new Predicate[list.size()];
                criteriaQuery.where(list.toArray(pre));
                return cb.and(list.toArray(pre));
            }
        }, pageable).getContent();
        Map<String, Object> map = new HashMap<>();
        for (Exam s:content){
            s.setExamSiteName(s.getExamSite().getExamSiteName());
        }
        map.put("data", content);
        map.put("size", examService.findAllCount());
        return map;
    }

    //考官智能分配
    @GetMapping("/smartDistribution")
    @ResponseBody
    public String distributionExaminer(Model model, Long id) {
        Exam exams = examService.findById(id);
        String examType=examService.findExamType(id).getExamType();
        String district=exams.getExamSite().getDistrict();
        String subjectName= exams.getStudySubject();
        List<Examiner> examiners = examinerService.findExaminerBy(examType,district,subjectName);
        System.out.println(examiners.size());
        System.out.println(examiners);
        if (examiners.size()==0){//判断符合条件的考官数量是否等于0
            return "no";
        }else if (examinerService.findAllCountViewExaminer(id) < examService.findQuantity(id).getQuantity() && examinerService.findAllCountViewExaminer(id) != 0) {//符合条件考官不等于0，判断对应考试是否已经分配过考官
            int v = examService.findQuantity(id).getQuantity() - examinerService.findAllCountViewExaminer(id);
            System.out.println(v);
            if (examiners.size() <= v && examiners.size()!=0){
                String ids = "";
                int i = 0;
                for (Examiner e : examiners) {
                    examinerService.updateExamId(id, e.getId());
                    i++;
                }
                //考官不够的怎么办？
                int j = v - i;
                List<Examiner> examiners1 = examinerService.findExaminerByRandom(examService.findExamType(id).getExamType(), exams.getStudySubject());
                if (examiners1.size() < j) {
                    return "no";
                } else {
                    int s = 1;
                    for (Examiner eList : examiners1) {
                        examinerService.updateExamId(id, eList.getId());
                        if (s == j) {
                            break;
                        }
                        s++;
                    }
                }
                //String ss=ids.substring(0,ids.length()-1);//当循环结束后截取最后一个逗号
                System.out.println("看看" + i);
                //examService.updateExaminer(ss,id);
            } else {
                int i = 1;
                for (Examiner e : examiners) {
                    //ids=ids+e.getId()+",";//for循环id组成一条用逗号隔开的字符串输出
                    examinerService.updateExamId(id, e.getId());
                    if (i == v) {
                        break;
                    }
                    i++;
                }
            }
            examService.updateState(1, id);//state:1代表考官分配完成
        } else if (examiners.size() <= examService.findQuantity(id).getQuantity()) {//符合条件考官不等于0，没有分配过走这边，且考官数量小于需求数量的。
            String ids = "";
            int i = 0;
            for (Examiner e : examiners) {
                //System.out.println(e.getId());
                //String ids=e.getId().toString();
                //ids=ids+e.getId()+",";//for循环id组成一条用逗号隔开的字符串输出
                examinerService.updateExamId(id, e.getId());
                i++;
                //System.out.println("看看"+ids);
                //String ids=StringUtils.join(e.getId(), ",");
                //examService.updateExaminer(ids,id);
            }
            //考官不够的怎么办？
            int j = examService.findQuantity(id).getQuantity() - i;
            List<Examiner> examiners1 = examinerService.findExaminerByRandom(examService.findExamType(id).getExamType(), exams.getStudySubject());
            if (examiners1.size() < j) {
                return "no";
            } else {
                int s = 1;
                for (Examiner eList : examiners1) {
                    examinerService.updateExamId(id, eList.getId());
                    if (s == j) {
                        break;
                    }
                    s++;
                }
            }
            //String ss=ids.substring(0,ids.length()-1);//当循环结束后截取最后一个逗号
            System.out.println("看看" + i);
            //examService.updateExaminer(ss,id);
        } else {//没有分配过考官数量满足需求数量的额
            int i = 1;
            for (Examiner e : examiners) {
                examinerService.updateExamId(id, e.getId());
                if (i == examService.findQuantity(id).getQuantity()) {
                    break;
                }
                i++;
            }
            System.out.println(i);
        }
        examService.updateState(1, id);//state:1代表考官分配完成
        return "ok";
    }

    //考官手动分配
    @GetMapping("/manualExaminerPage")
    public ModelAndView manualExaminer(Long id, Model model) {
        Exam exam = examService.findById(id);
        model.addAttribute("exam", exam);
        return new ModelAndView("admin/manualExaminer-list.html", "model", model);
    }

    //手动分配考官列表
    @GetMapping("/manualExaminer")
    @ResponseBody
    public Map<String, Object> manualExaminer(Long id, Integer page, Integer limit) {
        page--;
        Pageable pageable = PageRequest.of(page, limit);
        List<Examiner> content = examinerService.findExaminer(pageable).getContent();
        Map<String, Object> map = new HashMap<>();
        map.put("data", content);
        map.put("size", examinerService.findAllCountExaminer());
        return map;
    }

    //手动分配多个
    @GetMapping("/manualAllExaminer")
    @ResponseBody
    public int manualAllExaminer(Long id, @RequestParam("ids") String ids) {
        // 接收包含examinerId的字符串，并将它分割成字符串数组
        String[] exaList = ids.split(",");
        // 将字符串数组转为List<Intger> 类型
        List<Long> LString = new ArrayList<Long>();
        for (String str : exaList) {
            LString.add(new Long(str));
        }
        System.out.println("=====" + LString);
        // 调用service层的批量删除函数
        int i = examinerService.updateManualExamId(id, LString);
        examService.updateState(1, id);//state:1代表考官分配完成
        return i;
    }

    //分配的考官页面获取
    @GetMapping("/viewExaminerPage")
    public ModelAndView viewExaminerPage(Long id, Model model) {
        Exam exam = examService.findById(id);
        model.addAttribute("exam", exam);
        System.out.println(id);
        return new ModelAndView("admin/viewExaminer-list.html", "model", model);
    }

    //分配的考官
    @GetMapping("/viewExaminer")
    @ResponseBody
    public Map<String, Object> viewExaminer(Long id, Integer page, Integer limit) {
        page--;
        Pageable pageable = PageRequest.of(page, limit);
        List<Examiner> content = examinerService.findViewExaminer(id, pageable).getContent();
        Map<String, Object> map = new HashMap<>();
        /*for (Examiner s:content
        ) {
            s.setDistrictName(s.getDistrict().getDistrictName());
        }*/
        map.put("data", content);
        map.put("size", examinerService.findAllCountViewExaminer(id));
        return map;
    }

    //删除单个分配考官
    @PostMapping("/deleteViewExaminer")
    @ResponseBody
    public int deleteViewExaminer(Long id, int ids) {
        int i = examinerService.deleteViewExaminer("缺席",ids);
        System.out.println("======" + id);
        examService.updateState(2, id);
        return i;
    }

    //删除多个分配考官
    @PostMapping("/deleteAllViewExaminer")
    @ResponseBody
    public int deleteAllViewExaminer(@RequestParam("id") Long id, @RequestParam("ids") String ids) {
        // 接收包含stuId的字符串，并将它分割成字符串数组
        String[] stuList = ids.split(",");
        // 将字符串数组转为List<Intger> 类型
        List<Long> LString = new ArrayList<Long>();
        for (String str : stuList) {
            LString.add(new Long(str));
        }
        System.out.println("=====" + LString);
        // 调用service层的批量删除函数
        int i = examinerService.deleteAllViewExaminer("缺席",LString);
        examService.updateState(2, id);//state:2表示分配了考官 ，但有的来不到（缺席）
        return i;
    }


    //考官状态查询
    @GetMapping("/examinerStatus")
    @ResponseBody
    public Map<String, Object> examinerStatus(Integer page, Integer limit) {
        page--;
        Pageable pageable = PageRequest.of(page, limit);
        List<Examiner> content = examinerService.findAll(pageable).getContent();
        Map<String, Object> map = new HashMap<>();
        /*for (Examiner s:content
        ) {
            s.setDistrictName(s.getDistrict().getDistrictName());
        }*/
        map.put("data", content);
        map.put("size", examinerService.findAllCount());
        return map;
    }

//查看评价结果
    /*//结束考试列表获取
    @GetMapping("/evaluationExam")
    @ResponseBody
    public Map<String, Object> evaluationExam(Integer page, Integer limit) {
        page--;
        //获取当前时间
        Pageable pageable = PageRequest.of(page, limit);
        List<Exam> content = examService.findAllEvaluation(pageable).getContent();
        Map<String, Object> map = new HashMap<>();
        for (Exam s : content
        ) {
            s.setExamSiteName(s.getExamSite().getExamSiteName());
        }
        map.put("data", content);
        map.put("size", examService.findAllEvaluationCount());
        return map;
    }*/

    @GetMapping("/evaluationRecording")
    @ResponseBody
    public Map<String, Object> evaluationRecording(Integer page, Integer limit) {
        page--;
        //获取当前时间
        Pageable pageable = PageRequest.of(page, limit);
        List<EvaluationRecording> content = evaluationRecordingService.findAllEvaluationRecording(pageable).getContent();
        Map<String, Object> map = new HashMap<>();
        map.put("data", content);
        map.put("size", evaluationRecordingService.findAllCount());
        return map;

    }

    //搜索评价结果
    @GetMapping("/LikeEvaluationRecording")
    @ResponseBody
    public Map<String, Object> LikeEvaluationRecording(Integer page, Integer limit, EvaluationRecording evaluationRecording) {
        page--;
        Pageable pageable = PageRequest.of(page, limit);
        List<EvaluationRecording> content = evaluationRecordingService.findAll(new Specification<EvaluationRecording>() {
            @Override
            public Predicate toPredicate(Root<EvaluationRecording> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<>();
                //根据dptName 模糊查询Department
                if (StringUtils.isNotBlank(evaluationRecording.getPrincipal())) {
                    list.add(cb.like(root.get("principal").as(String.class), "%" + evaluationRecording.getPrincipal() + "%"));
                }
                if (StringUtils.isNotBlank(evaluationRecording.getExaminer())) {
                    list.add(cb.like(root.get("examiner").as(String.class), "%" + evaluationRecording.getExaminer() + "%"));
                }
                Predicate[] pre = new Predicate[list.size()];
                criteriaQuery.where(list.toArray(pre));
                return cb.and(list.toArray(pre));
            }
        }, pageable).getContent();
        Map<String, Object> map = new HashMap<>();
        map.put("data", content);
        map.put("size", evaluationRecordingService.findAllCount());
        return map;
    }
}

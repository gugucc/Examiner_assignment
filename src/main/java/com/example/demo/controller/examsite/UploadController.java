package com.example.demo.controller.examsite;


import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.example.demo.dto.Examiner;
import com.example.demo.service.ExaminerService;
import com.example.demo.utils.EasyPoiUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping
public class UploadController {
    @Autowired
    private ExaminerService examinerService;
   /* @Autowired
    private DistrictService districtService;*/
    /**
     * 测试导入
     */
    //接受表单提交请求
    @RequestMapping(value="/ImportExcel")
    @ResponseBody
    public Map<String,Object> ImportExcel(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        Assert.notNull(file, "上传文件不能为空");
        //String filepath = request.getServletContext().getRealPath("/");
        //获取跟目录
        try {
            File path = new File(ResourceUtils.getURL("classpath:").getPath());
            File filepath = new File(path.getAbsolutePath(),"static/excel/");
            //获取文件名和后缀
            String filename = System.currentTimeMillis()+file.getOriginalFilename();
            //确保路径存在
            if(!filepath.exists()) filepath.mkdirs();
            System.out.println("upload url:"+filepath.getAbsolutePath());

            String savepath = filepath+"/"+filename;
            System.out.println("轮播图保存路径:"+savepath);

                //保存文件到服务器
                file.transferTo(new File(savepath));
                System.out.println(savepath);


            List<Examiner> list=EasyPoiUtils.importExcel(new File(savepath),
                    Examiner.class, new ImportParams()
            );
            list.forEach((user) -> {
                Examiner examinerNew=new Examiner();
                examinerNew.setExaminerName(user.getExaminerName());
                examinerNew.setIdCard(user.getIdCard());
                examinerNew.setSex(user.getSex());
                examinerNew.setNation(user.getNation());
                examinerNew.setPolitical(user.getPolitical());
                examinerNew.setExamType(user.getExamType());
                examinerNew.setSubjectName(user.getSubjectName());
                examinerNew.setCompanyName(user.getCompanyName());
                examinerNew.setPosition(user.getPosition());
                examinerNew.setMailingAddress(user.getMailingAddress());
                examinerNew.setFixedTel(user.getFixedTel());
                examinerNew.setTel(user.getTel());
                examinerNew.setEmail(user.getEmail());
                examinerNew.setDistrict(user.getDistrict());
                examinerNew.setState(user.getState());
                examinerService.saveOne(examinerNew);
                System.out.println(user);
//返回json
                map.put("msg","ok");
                map.put("code",200);

            });

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }
        /*System.out.println(username);*/
        return map;
    }

    /**
     * 测试单sheet导出
     *
     * @throws IOException
     */
    @RequestMapping("/ExportExcel")
    @ResponseBody
    public void examinerExportExcel() throws IOException {
        List<Examiner> content = examinerService.findAllList();
        System.out.println(content);
        EasyPoiUtils.exportExcel(Examiner.class, content, "src/main/resources/excel/", "考官信息表.xls");
    }

}
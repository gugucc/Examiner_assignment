package com.example.demo.service;

import com.example.demo.dto.Exam;
import com.example.demo.dto.Examiner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ExaminerService {
    Page<Examiner> findAll(Pageable pageable);

    List<Examiner> findAllList();


    Page<Examiner> findExaminer(Pageable pageable);

    int findAllCountExaminer();

    int findAllCount();

    Examiner findById(Long id);
    void saveOne(Examiner examiner);

    int deleteExaminer(int id);
    int deleteAllExaminer(List<Long> examList);

    int updateState(String state,Long id);
    int updateStateE(String state,Long id);

    int deleteViewExaminer(String state,int id);
    int deleteAllViewExaminer(String state,List<Long> examList);

    int updateManualExamId(Long id,List<Long> idList);

    Page<Examiner> findViewExaminer(Long id, Pageable pageable);
    int findAllCountViewExaminer(Long id);

    Examiner findOneByIdCard(String idCard);
    List<Examiner> findExaminerBy(String examType,String district,String subjectName);
    List<Examiner> findExaminerByRandom(String examType,String subjectName);
    int updateExamId(Long id,Long ids);
    Examiner findExaminerByDistrict(String district);

    int updateEvaluationState(Long id);

    Page<Examiner> findAll(Specification<Examiner> examinerSpecification, Pageable pageable);
}

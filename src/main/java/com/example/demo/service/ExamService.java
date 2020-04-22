package com.example.demo.service;

import com.example.demo.dto.Exam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface ExamService {

    Page<Exam> findAll(Pageable pageable);
    int findAllCount();

    Page<Exam> findAllExamByPrincipal(Long id,Pageable pageable);
    int findAllCountPrincipal(Long id);

    Page<Exam> findEndExam(Long id,Pageable pageable);
    int findAllEndExam(Long id);
    List<Exam> findAllList();

    Exam findById(Long id);
    Exam findQuantity(Long id);
    Exam findExamType(Long id);
    Exam findDistrictByExamSite();
    void saveOne(Exam exam);

    int updateExaminer(String ids,Long id);

    int deleteExam(int id);

    int deleteAllExam(List<Long> ExamList);

    int updateState(int state,Long id);

    Exam findOneByName(String examName);

    Page<Exam> findAll(Specification<Exam> examSpecification, Pageable pageable);
}

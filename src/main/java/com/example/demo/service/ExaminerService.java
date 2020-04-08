package com.example.demo.service;

import com.example.demo.dto.Examiner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ExaminerService {
    Page<Examiner> findAll(Pageable pageable);

    List<Examiner> findAllList();


    int findAllCount();

    Examiner findById(Long id);
    void saveOne(Examiner examiner);

    int deleteExaminer(int id);
    int deleteAllExaminer(List<Long> examList);
    Examiner findOneByIdCard(String idCard);

    Page<Examiner> findAll(Specification<Examiner> examinerSpecification, Pageable pageable);
}

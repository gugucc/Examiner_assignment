package com.example.demo.service;

import com.example.demo.dto.DictExamType;
import com.example.demo.dto.DictPolitical;
import com.example.demo.dto.Examiner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface ExamTypeService {
    Page<DictExamType> findAll(Pageable pageable);
    int findAllCount();
    List<DictExamType> findAllList();

    DictExamType findById(Long id);
    void saveOne(DictExamType examType);

    int deleteExamType(int id);

    int deleteAllExamType(List<Long> examTypeList);

    DictExamType findOneByName(String typeNmae);


    Page<DictExamType> findAll(Specification<DictExamType> examTypeSpecification, Pageable pageable);

}

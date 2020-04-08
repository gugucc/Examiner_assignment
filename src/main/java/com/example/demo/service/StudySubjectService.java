package com.example.demo.service;


import com.example.demo.dto.DictNation;
import com.example.demo.dto.DictStudySubject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface StudySubjectService {
    Page<DictStudySubject> findAll(Pageable pageable);
    int findAllCount();
    List<DictStudySubject> findAllList();

    DictStudySubject findById(Long id);
    void saveOne(DictStudySubject studySubject);

    int deleteStudySubject(int id);

    int deleteAllStudySubject(List<Long> studySubjectList);

    DictStudySubject findOneByName(String studySubjectName);

    Page<DictStudySubject> findAll(Specification<DictStudySubject> studySubjectSpecification, Pageable pageable);
}

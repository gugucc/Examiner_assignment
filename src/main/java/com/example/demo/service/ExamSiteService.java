package com.example.demo.service;

import com.example.demo.dto.DictNation;
import com.example.demo.dto.ExamSite;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface ExamSiteService {
    Page<ExamSite> findAll(Pageable pageable);
    int findAllCount();

    List<ExamSite> findAllList();

    ExamSite findById(Long id);
    void saveOne(ExamSite examSite);

    int deleteExamSite(int id);

    int deleteAllExamSite(List<Long> examSiteList);

    ExamSite findOneByName(String examSiteName);

    Page<ExamSite> findAll(Specification<ExamSite> examSiteSpecification, Pageable pageable);
}

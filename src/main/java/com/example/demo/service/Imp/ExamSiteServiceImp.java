package com.example.demo.service.Imp;


import com.example.demo.dto.ExamSite;
import com.example.demo.repository.ExamSiteRepository;
import com.example.demo.service.ExamSiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ExamSiteServiceImp implements ExamSiteService {
    @Autowired
    private ExamSiteRepository examSiteRepository;

    @Override
    public Page<ExamSite> findAll(Pageable pageable) {
        return examSiteRepository.findAll(pageable);
    }

    @Override
    public int findAllCount() {
        return examSiteRepository.findAllCount();
    }

    @Override
    public List<ExamSite> findAllList() {
        return examSiteRepository.findAllList();
    }

    @Override
    public ExamSite findById(Long id) {
        return examSiteRepository.findById(id).get();
    }

    @Override
    public void saveOne(ExamSite examSite) {
        examSiteRepository.save(examSite);
    }

    @Override
    public int deleteExamSite(int id) {
        return examSiteRepository.deleteExamSite(id);
    }

    @Override
    public int deleteAllExamSite(List<Long> examSiteList) {
        return examSiteRepository.deleteAllExamSite(examSiteList);
    }

    @Override
    public ExamSite findOneByName(String examSiteName) {
        return examSiteRepository.findOneByName(examSiteName);
    }

    @Override
    public ExamSite findOneByPrincipalName(String principal) {
        return examSiteRepository.findOneByPrincipalName(principal);
    }

    @Override
    public Page<ExamSite> findAll(Specification<ExamSite> examSiteSpecification, Pageable pageable) {
        return examSiteRepository.findAll(examSiteSpecification,pageable);
    }
}

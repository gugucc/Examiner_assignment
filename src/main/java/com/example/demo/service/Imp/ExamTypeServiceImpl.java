package com.example.demo.service.Imp;

import com.example.demo.dto.DictExamType;
import com.example.demo.repository.ExamTypeRepository;
import com.example.demo.service.ExamTypeService;
import com.example.demo.service.ExaminerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ExamTypeServiceImpl implements ExamTypeService{

    @Autowired
    private ExamTypeRepository examTypeRepository;

    @Override
    public Page<DictExamType> findAll(Pageable pageable) {
        return examTypeRepository.findAll(pageable);
    }

    @Override
    public int findAllCount() {
        return examTypeRepository.findAllCount();
    }

    @Override
    public List<DictExamType> findAllList() {
        return examTypeRepository.findAllList();
    }

    @Override
    public DictExamType findById(Long id) {
        return examTypeRepository.findById(id).get();
    }

    @Override
    public void saveOne(DictExamType examType) {
        examTypeRepository.save(examType);
    }

    @Override
    public int deleteExamType(int id) {
        return examTypeRepository.deleteExamType(id);
    }

    @Override
    public int deleteAllExamType(List<Long> examTypeList) {
        return examTypeRepository.deleteAllExamType(examTypeList);
    }

    @Override
    public DictExamType findOneByName(String typeName) {
        return examTypeRepository.findOneByName(typeName);
    }

    @Override
    public Page<DictExamType> findAll(Specification<DictExamType> ets, Pageable pageable) {
        return examTypeRepository.findAll(ets,pageable);
    }
}

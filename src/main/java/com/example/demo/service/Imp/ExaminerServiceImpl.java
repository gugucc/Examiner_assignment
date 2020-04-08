package com.example.demo.service.Imp;

import com.example.demo.dto.Examiner;
import com.example.demo.repository.ExaminerRepository;
import com.example.demo.service.ExaminerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ExaminerServiceImpl implements ExaminerService {
    @Autowired
    private ExaminerRepository examinerRepository;

    @Override
    public Page<Examiner> findAll(Pageable pageable) {
        return examinerRepository.findAll(pageable);
    }

    @Override
    public List<Examiner> findAllList() {
        return examinerRepository.findAllList();
    }



    @Override
    public int findAllCount() {
        return examinerRepository.findAllCount();
    }

    @Override
    public Examiner findById(Long id) {
        return examinerRepository.findById(id).get();
    }

    @Override
    public void saveOne(Examiner examiner) {
        examinerRepository.save(examiner);
    }

    @Override
    public int deleteExaminer(int id) {
        return examinerRepository.deleteExaminer(id);
    }

    @Override
    public int deleteAllExaminer(List<Long> examList) {
        return examinerRepository.deleteAllExaminer(examList);
    }

    @Override
    public Examiner findOneByIdCard(String idCard) {
        return examinerRepository.findOneByIdCard(idCard);
    }

    @Override
    public Page<Examiner> findAll(Specification<Examiner> epc, Pageable pageable) {
        return examinerRepository.findAll(epc,pageable);
    }
}

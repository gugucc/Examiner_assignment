package com.example.demo.service.Imp;

import com.example.demo.dto.Exam;
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
    public Page<Examiner> findExaminer(Pageable pageable) {
        return examinerRepository.findExaminer(pageable);
    }

    @Override
    public int findAllCountExaminer() {
        return examinerRepository.findAllCountExaminer();
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
    public int updateState(String state, Long id) {
        return examinerRepository.updateState(state,id);
    }

    @Override
    public int updateStateE(String state, Long id) {
        return examinerRepository.updateStateE(state,id);
    }


    @Override
    public int deleteViewExaminer(String state,int id) {
        return examinerRepository.deleteViewExaminer(state,id);
    }

    @Override
    public int deleteAllViewExaminer(String state,List<Long> examList) {
        return examinerRepository.deleteAllViewExaminer(state,examList);
    }

    @Override
    public int updateManualExamId(Long id, List<Long> idList) {
        return examinerRepository.updateManualExamId(id,idList);
    }

    @Override
    public Page<Examiner> findViewExaminer(Long id, Pageable pageable) {
        return examinerRepository.findViewExaminer(id,pageable);
    }

    @Override
    public int findAllCountViewExaminer(Long id) {
        return examinerRepository.findAllCountViewExaminer(id);
    }

    @Override
    public Examiner findOneByIdCard(String idCard) {
        return examinerRepository.findOneByIdCard(idCard);
    }

    @Override
    public List<Examiner> findExaminerBy(String examType, String district,String subjectName) {
        return examinerRepository.findExaminerBy(examType,district,subjectName);
    }

    @Override
    public List<Examiner> findExaminerByRandom(String examType, String subjectName) {
        return examinerRepository.findExaminerByRandom(examType,subjectName);
    }

    @Override
    public int updateExamId(Long id, Long ids) {
        return examinerRepository.updateExamId(id,ids);
    }


    @Override
    public Examiner findExaminerByDistrict(String district) {
        return examinerRepository.findExaminerByDistrict(district);
    }

    @Override
    public int updateEvaluationState(Long id) {
        return examinerRepository.updateEvaluationState(id);
    }

    @Override
    public Page<Examiner> findAll(Specification<Examiner> epc, Pageable pageable) {
        return examinerRepository.findAll(epc,pageable);
    }
}

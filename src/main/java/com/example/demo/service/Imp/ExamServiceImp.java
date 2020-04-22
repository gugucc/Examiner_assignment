package com.example.demo.service.Imp;

import com.example.demo.dto.Exam;
import com.example.demo.repository.ExamRepository;
import com.example.demo.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ExamServiceImp implements ExamService {

    @Autowired
    private ExamRepository examRepository;

    @Override
    public Page<Exam> findAll(Pageable pageable) {
        return examRepository.findAll(pageable);
    }

    @Override
    public int findAllCount() {
        return examRepository.findAllCount();
    }

    @Override
    public Page<Exam> findAllExamByPrincipal(Long id, Pageable pageable) {
        return examRepository.findAllExamByPrincipal(id,pageable);
    }

    @Override
    public int findAllCountPrincipal(Long id) {
        return examRepository.findAllCountPrincipal(id);
    }


    @Override
    public Page<Exam> findEndExam(Long id, Pageable pageable) {
        return examRepository.findEndExam(id,pageable);
    }

    @Override
    public int findAllEndExam(Long id) {
        return examRepository.findAllEndExam(id);
    }


    @Override
    public List<Exam> findAllList() {
        return examRepository.findAllList();
    }

    @Override
    public Exam findById(Long id) {
        return examRepository.findById(id).get() ;
    }

    @Override
    public Exam findQuantity(Long id) {
        return examRepository.findQuantity(id);
    }

    @Override
    public Exam findExamType(Long id) {
        return examRepository.findExamType(id);
    }

   @Override
    public Exam findDistrictByExamSite() {
        return examRepository.findDistrictByExamSite();
    }

    @Override
    public void saveOne(Exam exam) {
        examRepository.save(exam);
    }

    @Override
    public int updateExaminer(String ids, Long id) {
        return examRepository.updateExaminer(ids,id);
    }




    @Override
    public int deleteExam(int id) {
        return examRepository.deleteExam(id);
    }

    @Override
    public int deleteAllExam(List<Long> ExamList) {
        return examRepository.deleteAllExam(ExamList);
    }

    @Override
    public int updateState(int state, Long id) {
        return examRepository.updateState(state,id);
    }

    @Override
    public Exam findOneByName(String examName) {
        return examRepository.findOneByName(examName);
    }

    @Override
    public Page<Exam> findAll(Specification<Exam> ets, Pageable pageable) {
        return examRepository.findAll(ets,pageable);
    }
}

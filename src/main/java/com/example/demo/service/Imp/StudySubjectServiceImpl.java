package com.example.demo.service.Imp;

import com.example.demo.dto.DictStudySubject;
import com.example.demo.repository.StudySubjectRepository;
import com.example.demo.service.StudySubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudySubjectServiceImpl implements StudySubjectService {
    @Autowired
    private StudySubjectRepository studySubjectRepository;

    @Override
    public Page<DictStudySubject> findAll(Pageable pageable) {
        return studySubjectRepository.findAll(pageable);
    }

    @Override
    public int findAllCount() {
        return studySubjectRepository.findAllCount();
    }

    @Override
    public List<DictStudySubject> findAllList() {
        return studySubjectRepository.findAllList();
    }

    @Override
    public DictStudySubject findById(Long id) {
        return studySubjectRepository.findById(id).get();
    }

    @Override
    public void saveOne(DictStudySubject studySubject) {
        studySubjectRepository.save(studySubject);
    }

    @Override
    public int deleteStudySubject(int id) {
        return studySubjectRepository.deleteStudySubject(id);
    }

    @Override
    public int deleteAllStudySubject(List<Long> studySubjectList) {
        return studySubjectRepository.deleteAllStudySubject(studySubjectList);
    }

    @Override
    public DictStudySubject findOneByName(String studySubjectName) {
        return studySubjectRepository.findOneByName(studySubjectName);
    }

    @Override
    public Page<DictStudySubject> findAll(Specification<DictStudySubject> sts, Pageable pageable) {
        return studySubjectRepository.findAll(sts,pageable);
    }
}

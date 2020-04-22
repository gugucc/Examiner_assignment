package com.example.demo.service.Imp;

import com.example.demo.dto.EvaluationRecording;
import com.example.demo.dto.Exam;
import com.example.demo.repository.EvaluationRecordingRepository;
import com.example.demo.service.EvaluationRecordingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EvaluationRecordingServiceImp implements EvaluationRecordingService {
    @Autowired
    private EvaluationRecordingRepository evaluationRecordingRepository;


    @Override
    public List<EvaluationRecording> findByNumber(String stuNumber) {
        return null;
    }

    @Override
    public void save(EvaluationRecording evaluationRecording) {
        evaluationRecordingRepository.save(evaluationRecording);
    }

    @Override
    public List<EvaluationRecording> findAll() {
        return null;
    }

    @Override
    public Page<EvaluationRecording> findAllEvaluationRecording(Pageable pageable) {
        return evaluationRecordingRepository.findAllEvaluationRecording(pageable);
    }

    @Override
    public int findAllCount() {
        return evaluationRecordingRepository.findAllCount();
    }

    @Override
    public EvaluationRecording findOneByNumber(String userNumber, String teacherNumber) {
        return null;
    }

    @Override
    public void deleteEvaluationRecording(Long id) {

    }

    @Override
    public void deleteEvaluationRecordingById(Long id) {

    }

    @Override
    public int deleteEvaluationRecordingByTeacherId(Long teacherNumber) {
        return 0;
    }

    @Override
    public Page<EvaluationRecording> findAll(Specification<EvaluationRecording> ers, Pageable pageable) {
        return evaluationRecordingRepository.findAll(ers,pageable);
    }
}

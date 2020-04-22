package com.example.demo.service;

import com.example.demo.dto.EvaluationRecording;
import com.example.demo.dto.Exam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface EvaluationRecordingService {
    List<EvaluationRecording> findByNumber(String stuNumber);

    void save(EvaluationRecording evaluationRecording);

    List<EvaluationRecording> findAll();

    Page<EvaluationRecording> findAllEvaluationRecording(Pageable pageable);

    int findAllCount();

    EvaluationRecording findOneByNumber(String userNumber, String teacherNumber);

    void deleteEvaluationRecording(Long id);

    void deleteEvaluationRecordingById(Long id);

    int deleteEvaluationRecordingByTeacherId(Long teacherNumber);

    Page<EvaluationRecording> findAll(Specification<EvaluationRecording> evaluationRecordingSpecification, Pageable pageable);
}

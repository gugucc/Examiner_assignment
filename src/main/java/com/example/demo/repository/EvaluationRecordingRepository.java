package com.example.demo.repository;

import com.example.demo.dto.EvaluationRecording;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public interface EvaluationRecordingRepository extends JpaRepository<EvaluationRecording,Long> {

    @Query(value = "select * from evaluation_recording", nativeQuery = true)
    Page<EvaluationRecording> findAllEvaluationRecording(Pageable pageable);

    @Query(value = "select count(*) from evaluation_recording", nativeQuery = true)
    int findAllCount();

    @Query(value = "select * from evaluation_recording where user_number=?1",nativeQuery = true)
    List<EvaluationRecording> findByNumber(String stuNumber);

    @Query(value = "delete from evaluation_recording where id = ?1",nativeQuery = true)
    @Modifying
    @Transactional
    void deleteEvaluationRecording(Long id);

    Page<EvaluationRecording> findAll(Specification<EvaluationRecording> ers, Pageable pageable);
}

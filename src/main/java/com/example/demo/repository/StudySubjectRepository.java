package com.example.demo.repository;


import com.example.demo.dto.DictNation;
import com.example.demo.dto.DictStudySubject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StudySubjectRepository extends JpaRepository<DictStudySubject,Long> {
    @Query(value = "select * from dict_study_subject", nativeQuery = true)
    Page<DictStudySubject> findAll(Pageable pageable);

    @Query(value = "select count(*) from dict_study_subject", nativeQuery = true)
    int findAllCount();

    @Query(value = "select * from dict_study_subject",nativeQuery = true)
    List<DictStudySubject> findAllList();


    @Modifying
    @Transactional
    @Query(value = "delete from dict_study_subject where id=?1",nativeQuery = true)
    int deleteStudySubject(int id);
    @Modifying
    @Transactional
    @Query(value = "delete from dict_study_subject where id in (?1)",nativeQuery = true)
    int deleteAllStudySubject(List<Long> ids);

    @Query(value = "from dict_study_subject where study_subject_name= ?1",nativeQuery = true)
    DictStudySubject findOneByName(String studySubjectName);

    Page<DictStudySubject> findAll(Specification<DictStudySubject> sts, Pageable pageable);
}

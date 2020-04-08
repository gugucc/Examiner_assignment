package com.example.demo.repository;

import com.example.demo.dto.DictExamType;
import com.example.demo.dto.Examiner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ExamTypeRepository extends JpaRepository<DictExamType,Long> {
    @Query(value = "select * from dict_exam_type", nativeQuery = true)
    Page<DictExamType> findAll(Pageable pageable);

    @Query(value = "select count(*) from dict_exam_type", nativeQuery = true)
    int findAllCount();

    @Query(value = "select * from dict_exam_type",nativeQuery = true)
    List<DictExamType> findAllList();

    @Modifying
    @Transactional
    @Query(value = "delete from dict_exam_type where id=?1",nativeQuery = true)
    int deleteExamType(int id);
    @Modifying
    @Transactional
    @Query(value = "delete from dict_exam_type where id in (?1)",nativeQuery = true)
    int deleteAllExamType(List<Long> ids);

    @Query(value = "from dict_exam_type where type_name= ?1",nativeQuery = true)
    DictExamType findOneByName(String typeName);

    Page<DictExamType> findAll(Specification<DictExamType> ets, Pageable pageable);
}

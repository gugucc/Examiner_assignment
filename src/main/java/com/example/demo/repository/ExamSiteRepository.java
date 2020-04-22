package com.example.demo.repository;

import com.example.demo.dto.DictNation;
import com.example.demo.dto.ExamSite;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ExamSiteRepository extends JpaRepository<ExamSite,Long> {
    @Query(value = "select * from exam_site", nativeQuery = true)
    Page<ExamSite> findAll(Pageable pageable);

    @Query(value = "select count(*) from exam_site", nativeQuery = true)
    int findAllCount();

    @Query(value = "select * from exam_site",nativeQuery = true)
    List<ExamSite> findAllList();


    @Modifying
    @Transactional
    @Query(value = "delete from exam_site where id=?1",nativeQuery = true)
    int deleteExamSite(int id);
    @Modifying
    @Transactional
    @Query(value = "delete from exam_site where id in (?1)",nativeQuery = true)
    int deleteAllExamSite(List<Long> ids);

    @Query(value = "select * from exam_site where exam_site_name= ?1",nativeQuery = true)
    ExamSite findOneByName(String examSiteName);

    @Query(value = "select * from exam_site where principal= ?1",nativeQuery = true)
    ExamSite findOneByPrincipalName(String principal);

    Page<ExamSite> findAll(Specification<ExamSite> ets, Pageable pageable);
}

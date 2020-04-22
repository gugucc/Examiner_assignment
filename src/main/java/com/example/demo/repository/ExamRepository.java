package com.example.demo.repository;

import com.example.demo.dto.DictNation;
import com.example.demo.dto.Exam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ExamRepository extends JpaRepository<Exam,Long> {
    @Query(value = "select * from Exam", nativeQuery = true)
    Page<Exam> findAll(Pageable pageable);

    @Query(value = "select count(*) from Exam", nativeQuery = true)
    int findAllCount();

    @Query(value = "select * from Exam where exam_site_id=?1", nativeQuery = true)
    Page<Exam> findAllExamByPrincipal(Long id, Pageable pageable);

    @Query(value = "select count(*) from Exam where exam_site_id=?1", nativeQuery = true)
    int findAllCountPrincipal(Long id);

    @Query(value = "select * from Exam where state=4 and exam_site_id=?1", nativeQuery = true)
    Page<Exam> findEndExam(Long id,Pageable pageable);
    @Query(value = "select count(*) from Exam where state=4 and exam_site_id=?1", nativeQuery = true)
    int findAllEndExam(Long id);

    @Query(value = "select * from Exam",nativeQuery = true)
    List<Exam> findAllList();


    @Modifying
    @Transactional
    @Query(value = "delete from Exam where id=?1",nativeQuery = true)
    int deleteExam(int id);
    @Modifying
    @Transactional
    @Query(value = "delete from Exam where id in (?1)",nativeQuery = true)
    int deleteAllExam(List<Long> ids);

    @Query(value = "select * from Exam where exam_name= ?1",nativeQuery = true)
    Exam findOneByName(String examName);

    @Query(value = "select id,quantity from Exam where id=?1",nativeQuery = true)
    Exam findQuantity(Long id);
    @Query(value = "select id,exam_type from Exam where id=?1",nativeQuery = true)
    Exam findExamType(Long id);

    @Modifying
    @Transactional
    @Query(value = "update Exam set examiner_id =?1 where id=?2 ",nativeQuery = true)
    int updateExaminer(String ids,Long id);

    @Modifying
    @Transactional
    @Query(value = "update Exam set state =?1 where id=?2 ",nativeQuery = true)
    int updateState(int state,Long id);



    @Query(value = "select * from exam where id=?1",nativeQuery = true)
    Exam findDistrictByExamSite();

    Page<Exam> findAll(Specification<Exam> ets, Pageable pageable);
}

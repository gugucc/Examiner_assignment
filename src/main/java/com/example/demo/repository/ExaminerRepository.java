package com.example.demo.repository;

import com.example.demo.dto.Examiner;
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
public interface ExaminerRepository extends JpaRepository<Examiner,Long> {
    @Query(value = "select * from examiner",nativeQuery = true)
    Page<Examiner> findAll(Pageable pageable);

    @Query(value = "select * from examiner",nativeQuery = true)
    List<Examiner> findAllList();

    @Query(value = "select * from Examiner where state='正常' and exam_id is null or exam_id=''", nativeQuery = true)
    Page<Examiner> findExaminer(Pageable pageable);

    @Query(value = "select count(*) from examiner where state='正常' and exam_id is null or exam_id=''",nativeQuery = true)
    int findAllCountExaminer();

    @Query(value = "select count(*) from examiner",nativeQuery = true)
    int findAllCount();

    @Modifying
    @Transactional
    @Query(value = "delete from examiner where id=?1",nativeQuery = true)
    int deleteExaminer(int id);
    @Modifying
    @Transactional
    @Query(value = "delete from Examiner where id in (?1)",nativeQuery = true)
    int deleteAllExaminer(List<Long> ids);

    @Modifying
    @Transactional
    @Query(value = "update examiner set exam_id=0,state=?1 where id=?2",nativeQuery = true)
    int deleteViewExaminer(String state,int id);

    @Modifying
    @Transactional
    @Query(value = "update Examiner set exam_id=0,state=?1 where id in (?2)",nativeQuery = true)
    int deleteAllViewExaminer(String state,List<Long> examList);

    @Modifying
    @Transactional
    @Query(value = "update Examiner set state=?1 where exam_id=?2",nativeQuery = true)
    int updateState(String state,Long id);

    @Modifying
    @Transactional
    @Query(value = "update Examiner set state=?1 where exam_id=?2",nativeQuery = true)
    int updateStateE(String state,Long id);

    @Query(value = "from Examiner where idCard = ?1")
    Examiner findOneByIdCard(String idCard);

    @Query(value = "select * from Examiner where exam_type=?1 and district=?2 and subject_name=?3 and state='正常'",nativeQuery = true)
    List<Examiner> findExaminerBy(String examType,String district,String subjectName);

    @Query(value = "select * from Examiner where exam_type=?1 and subject_name=?2 and state='正常' and exam_id is null or exam_id=''",nativeQuery = true)
    List<Examiner> findExaminerByRandom(String examType,String subjectName);

    @Query(value = "select * from Examiner where exam_id=?1", nativeQuery = true)
    Page<Examiner> findViewExaminer(Long id, Pageable pageable);

    @Query(value = "select count(*) from Examiner where exam_id=?1", nativeQuery = true)
    int findAllCountViewExaminer(Long id);

    @Modifying
    @Transactional
    @Query(value = "update Examiner set exam_id =?1,state='已分配' where id=?2 ",nativeQuery = true)
    int updateExamId(Long id,Long ids);

    @Modifying
    @Transactional
    @Query(value = "update Examiner set exam_id =?1,state='已分配' where id in (?2) ",nativeQuery = true)
    int updateManualExamId(Long id,List<Long> idList);

    @Modifying
    @Transactional
    @Query(value = "update Examiner set evaluation_State ='1' where id=?1 ",nativeQuery = true)
    int updateEvaluationState(Long id);

    @Query(value = "select * from Examiner where district=?1",nativeQuery = true)
    Examiner findExaminerByDistrict(String district);

    Page<Examiner> findAll(Specification<Examiner> epc, Pageable pageable);
}

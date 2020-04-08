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

    @Query(value = "from Examiner where idCard = ?1")
    Examiner findOneByIdCard(String idCard);

    Page<Examiner> findAll(Specification<Examiner> epc, Pageable pageable);
}

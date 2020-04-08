package com.example.demo.repository;

import com.example.demo.dto.DictConfirmResult;
import com.example.demo.dto.DictNation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ConfirmResultRepository extends JpaRepository<DictConfirmResult,Long> {
    @Query(value = "select * from dict_confirm_result", nativeQuery = true)
    Page<DictConfirmResult> findAll(Pageable pageable);

    @Query(value = "select count(*) from dict_confirm_result", nativeQuery = true)
    int findAllCount();

    @Query(value = "select * from dict_confirm_result",nativeQuery = true)
    List<DictConfirmResult> findAllList();


    @Modifying
    @Transactional
    @Query(value = "delete from dict_confirm_result where id=?1",nativeQuery = true)
    int deleteConfirmResult(int id);
    @Modifying
    @Transactional
    @Query(value = "delete from dict_confirm_result where id in (?1)",nativeQuery = true)
    int deleteAllConfirmResult(List<Long> ids);

    @Query(value = "from dict_confirm_result where confirm_result_name= ?1",nativeQuery = true)
    DictConfirmResult findOneByName(String nationName);

    Page<DictConfirmResult> findAll(Specification<DictConfirmResult> cts, Pageable pageable);
}

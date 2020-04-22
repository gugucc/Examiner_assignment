package com.example.demo.repository;

import com.example.demo.dto.DictDistrict;
import com.example.demo.dto.EvaluateItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface EvaluateItemRepository extends JpaRepository<EvaluateItem,Long> {
    @Query(value = "select * from evaluate_item", nativeQuery = true)
    Page<EvaluateItem> findAll(Pageable pageable);

    @Query(value = "select count(*) from evaluate_item", nativeQuery = true)
    int findAllCount();

    @Query(value = "select * from evaluate_item",nativeQuery = true)
    List<EvaluateItem> findAllList();

    @Modifying
    @Transactional
    @Query(value = "delete from evaluate_item where id=?1",nativeQuery = true)
    int deleteEvaluateItem(int id);
    @Modifying
    @Transactional
    @Query(value = "delete from evaluate_item where id in (?1)",nativeQuery = true)
    int deleteAllEvaluateItem(List<Long> ids);

    @Query(value = "select * from evaluate_item where evaluate_name=?1",nativeQuery = true)
    EvaluateItem findOneByName(String evaluateItemName);

    Page<EvaluateItem> findAll(Specification<EvaluateItem> evts, Pageable pageable);
}

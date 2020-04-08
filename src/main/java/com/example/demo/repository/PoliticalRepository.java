package com.example.demo.repository;

import com.example.demo.dto.DictNation;
import com.example.demo.dto.DictPolitical;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface PoliticalRepository extends JpaRepository<DictPolitical,Long> {
    @Query(value = "select * from dict_political", nativeQuery = true)
    Page<DictPolitical> findAll(Pageable pageable);

    @Query(value = "select count(*) from dict_political", nativeQuery = true)
    int findAllCount();

    @Query(value = "select * from dict_political",nativeQuery = true)
    List<DictPolitical> findAllList();

    @Modifying
    @Transactional
    @Query(value = "delete from dict_political where id=?1",nativeQuery = true)
    int deletePolitical(int id);
    @Modifying
    @Transactional
    @Query(value = "delete from dict_political where id in (?1)",nativeQuery = true)
    int deleteAllPolitical(List<Long> ids);

    @Query(value = "from dict_political where political_name= ?1",nativeQuery = true)
    DictPolitical findOneByName(String politicalName);

    Page<DictPolitical> findAll(Specification<DictPolitical> nts, Pageable pageable);
}

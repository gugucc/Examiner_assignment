package com.example.demo.repository;

import com.example.demo.dto.DictDistrict;
import com.example.demo.dto.DictNation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface NationRepository extends JpaRepository<DictNation,Long> {
    @Query(value = "select * from dict_nation", nativeQuery = true)
    Page<DictNation> findAll(Pageable pageable);

    @Query(value = "select count(*) from dict_nation", nativeQuery = true)
    int findAllCount();

    @Query(value = "select * from dict_nation",nativeQuery = true)
    List<DictNation> findAllList();


    @Modifying
    @Transactional
    @Query(value = "delete from dict_nation where id=?1",nativeQuery = true)
    int deleteNation(int id);
    @Modifying
    @Transactional
    @Query(value = "delete from dict_nation where id in (?1)",nativeQuery = true)
    int deleteAllNation(List<Long> ids);

    @Query(value = "from dict_nation where nation_name= ?1",nativeQuery = true)
    DictNation findOneByName(String nationName);

    Page<DictNation> findAll(Specification<DictNation> nts, Pageable pageable);
}

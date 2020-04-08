package com.example.demo.repository;

import com.example.demo.dto.DictDistrict;
import com.example.demo.dto.DictExamType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DistrictRepository extends JpaRepository<DictDistrict,Long> {
    @Query(value = "select * from dict_district", nativeQuery = true)
    Page<DictDistrict> findAll(Pageable pageable);

    @Query(value = "select count(*) from dict_district", nativeQuery = true)
    int findAllCount();

    @Query(value = "select * from dict_district",nativeQuery = true)
    List<DictDistrict> findAllList();

    @Modifying
    @Transactional
    @Query(value = "delete from dict_district where id=?1",nativeQuery = true)
    int deleteDistrict(int id);
    @Modifying
    @Transactional
    @Query(value = "delete from dict_district where id in (?1)",nativeQuery = true)
    int deleteAllDistrict(List<Long> ids);

    @Query(value = "from dict_district where district_name= ?1",nativeQuery = true)
    DictDistrict findOneByName(String districtName);

    Page<DictDistrict> findAll(Specification<DictDistrict> dts, Pageable pageable);
}

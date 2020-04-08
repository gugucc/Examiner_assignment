package com.example.demo.service;

import com.example.demo.dto.DictDistrict;
import com.example.demo.dto.DictExamType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface DistrictService {
    Page<DictDistrict> findAll(Pageable pageable);
    int findAllCount();
    List<DictDistrict> findAllList();

    DictDistrict findById(Long id);
    void saveOne(DictDistrict district);

    int deleteDistrict(int id);

    int deleteAllDistrict(List<Long> districtList);

    DictDistrict findOneByName(String districtNmae);

    Page<DictDistrict> findAll(Specification<DictDistrict> districtSpecification, Pageable pageable);
}

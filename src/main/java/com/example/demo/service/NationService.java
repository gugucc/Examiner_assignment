package com.example.demo.service;

import com.example.demo.dto.DictDistrict;
import com.example.demo.dto.DictNation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;


public interface NationService {
    Page<DictNation> findAll(Pageable pageable);
    int findAllCount();

    List<DictNation> findAllList();

    DictNation findById(Long id);
    void saveOne(DictNation nation);

    int deleteNation(int id);

    int deleteAllNation(List<Long> nationList);

    DictNation findOneByName(String nationName);

    Page<DictNation> findAll(Specification<DictNation> nationSpecification, Pageable pageable);
}

package com.example.demo.service;

import com.example.demo.dto.DictNation;
import com.example.demo.dto.DictPolitical;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface PoliticalService {
    Page<DictPolitical> findAll(Pageable pageable);
    int findAllCount();
    List<DictPolitical> findAllList();
    DictPolitical findById(Long id);
    void saveOne(DictPolitical political);

    int deletePolitical(int id);

    int deleteAllPolitical(List<Long> politicalList);

    DictPolitical findOneByName(String politicalName);

    Page<DictPolitical> findAll(Specification<DictPolitical> politicalSpecification, Pageable pageable);
}

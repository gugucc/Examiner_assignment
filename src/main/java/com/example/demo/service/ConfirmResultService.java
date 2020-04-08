package com.example.demo.service;

import com.example.demo.dto.DictConfirmResult;
import com.example.demo.dto.DictNation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ConfirmResultService {

    Page<DictConfirmResult> findAll(Pageable pageable);
    int findAllCount();

    List<DictConfirmResult> findAllList();

    DictConfirmResult findById(Long id);
    void saveOne(DictConfirmResult confirmResult);

    int deleteConfirmResult(int id);

    int deleteAllConfirmResult(List<Long> confirmResultList);

    DictConfirmResult findOneByName(String confirmResultName);

    Page<DictConfirmResult> findAll(Specification<DictConfirmResult> confirmResultSpecification, Pageable pageable);
}

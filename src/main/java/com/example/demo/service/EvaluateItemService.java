package com.example.demo.service;

import com.example.demo.dto.EvaluateItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface EvaluateItemService {
    Page<EvaluateItem> findAll(Pageable pageable);
    int findAllCount();
    List<EvaluateItem> findAllList();

    EvaluateItem findById(Long id);
    void saveOne(EvaluateItem evaluateItem);

    int deleteEvaluateItem(int id);

    int deleteAllEvaluateItem(List<Long> evaluateItemList);

    EvaluateItem findOneByName(String evaluateItemName);

    Page<EvaluateItem> findAll(Specification<EvaluateItem> evaluateItemSpecification, Pageable pageable);
}

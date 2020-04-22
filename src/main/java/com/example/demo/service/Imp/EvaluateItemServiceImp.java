package com.example.demo.service.Imp;

import com.example.demo.dto.EvaluateItem;
import com.example.demo.repository.DistrictRepository;
import com.example.demo.repository.EvaluateItemRepository;
import com.example.demo.service.EvaluateItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluateItemServiceImp implements EvaluateItemService{

    @Autowired
    private EvaluateItemRepository evaluateItemRepository;
    @Override
    public Page<EvaluateItem> findAll(Pageable pageable) {
        return evaluateItemRepository.findAll(pageable);
    }

    @Override
    public int findAllCount() {
        return evaluateItemRepository.findAllCount();
    }

    @Override
    public List<EvaluateItem> findAllList() {
        return evaluateItemRepository.findAllList();
    }

    @Override
    public EvaluateItem findById(Long id) {
        return evaluateItemRepository.findById(id).get();
    }

    @Override
    public void saveOne(EvaluateItem evaluateItem) {
        evaluateItemRepository.save(evaluateItem);
    }

    @Override
    public int deleteEvaluateItem(int id) {
        return evaluateItemRepository.deleteEvaluateItem(id);
    }

    @Override
    public int deleteAllEvaluateItem(List<Long> evaluateItemList) {
        return evaluateItemRepository.deleteAllEvaluateItem(evaluateItemList);
    }

    @Override
    public EvaluateItem findOneByName(String evaluateItemName) {
        return evaluateItemRepository.findOneByName(evaluateItemName);
    }

    @Override
    public Page<EvaluateItem> findAll(Specification<EvaluateItem> evts, Pageable pageable) {
        return evaluateItemRepository.findAll(evts,pageable);
    }
}

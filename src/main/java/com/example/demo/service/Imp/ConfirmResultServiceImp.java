package com.example.demo.service.Imp;

import com.example.demo.dto.DictConfirmResult;
import com.example.demo.repository.ConfirmResultRepository;
import com.example.demo.service.ConfirmResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ConfirmResultServiceImp implements ConfirmResultService {

    @Autowired
    private ConfirmResultRepository confirmResultRepository;

    @Override
    public Page<DictConfirmResult> findAll(Pageable pageable) {
        return confirmResultRepository.findAll(pageable);
    }

    @Override
    public int findAllCount() {
        return confirmResultRepository.findAllCount();
    }

    @Override
    public List<DictConfirmResult> findAllList() {
        return confirmResultRepository.findAllList();
    }

    @Override
    public DictConfirmResult findById(Long id) {
        return confirmResultRepository.findById(id).get();
    }

    @Override
    public void saveOne(DictConfirmResult confirmResult) {
        confirmResultRepository.save(confirmResult);
    }

    @Override
    public int deleteConfirmResult(int id) {
        return confirmResultRepository.deleteConfirmResult(id);
    }

    @Override
    public int deleteAllConfirmResult(List<Long> confirmResultList) {
        return confirmResultRepository.deleteAllConfirmResult(confirmResultList);
    }

    @Override
    public DictConfirmResult findOneByName(String confirmResultName) {
        return confirmResultRepository.findOneByName(confirmResultName);
    }

    @Override
    public Page<DictConfirmResult> findAll(Specification<DictConfirmResult> cts, Pageable pageable) {
        return confirmResultRepository.findAll(cts,pageable);
    }
}

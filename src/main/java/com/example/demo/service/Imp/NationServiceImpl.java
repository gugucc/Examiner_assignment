package com.example.demo.service.Imp;

import com.example.demo.dto.DictNation;
import com.example.demo.repository.NationRepository;
import com.example.demo.service.NationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class NationServiceImpl implements NationService {

    @Autowired
    private NationRepository nationRepository;

    @Override
    public Page<DictNation> findAll(Pageable pageable) {
        return nationRepository.findAll(pageable);
    }

    @Override
    public int findAllCount() {
        return nationRepository.findAllCount();
    }

    @Override
    public List<DictNation> findAllList() {
        return nationRepository.findAllList();
    }

    @Override
    public DictNation findById(Long id) {
        return nationRepository.findById(id).get();
    }

    @Override
    public void saveOne(DictNation nation) {
        nationRepository.save(nation);
    }

    @Override
    public int deleteNation(int id) {
        return nationRepository.deleteNation(id);
    }

    @Override
    public int deleteAllNation(List<Long> nationList) {
        return nationRepository.deleteAllNation(nationList);
    }

    @Override
    public DictNation findOneByName(String nationNmae) {
        return nationRepository.findOneByName(nationNmae);
    }

    @Override
    public Page<DictNation> findAll(Specification<DictNation> nts, Pageable pageable) {
        return nationRepository.findAll(nts,pageable);
    }
}

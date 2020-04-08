package com.example.demo.service.Imp;

import com.example.demo.dto.DictPolitical;
import com.example.demo.repository.PoliticalRepository;
import com.example.demo.service.PoliticalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PoliticalServiceImpl implements PoliticalService {

    @Autowired
    private PoliticalRepository politicalRepository;

    @Override
    public Page<DictPolitical> findAll(Pageable pageable) {
        return politicalRepository.findAll(pageable);
    }

    @Override
    public int findAllCount() {
        return politicalRepository.findAllCount();
    }

    @Override
    public List<DictPolitical> findAllList() {
        return politicalRepository.findAllList();
    }

    @Override
    public DictPolitical findById(Long id) {
        return politicalRepository.findById(id).get();
    }

    @Override
    public void saveOne(DictPolitical political) {
        politicalRepository.save(political);
    }

    @Override
    public int deletePolitical(int id) {
        return politicalRepository.deletePolitical(id);
    }

    @Override
    public int deleteAllPolitical(List<Long> politicalList) {
        return politicalRepository.deleteAllPolitical(politicalList);
    }

    @Override
    public DictPolitical findOneByName(String politicalName) {
        return null;
    }

    @Override
    public Page<DictPolitical> findAll(Specification<DictPolitical> politicalSpecification, Pageable pageable) {
        return null;
    }
}

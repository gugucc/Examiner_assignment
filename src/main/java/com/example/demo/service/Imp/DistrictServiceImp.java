package com.example.demo.service.Imp;

import com.example.demo.dto.DictDistrict;
import com.example.demo.repository.DistrictRepository;
import com.example.demo.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DistrictServiceImp implements DistrictService {
    @Autowired
    private DistrictRepository districtRepository;

    @Override
    public Page<DictDistrict> findAll(Pageable pageable) {
        return districtRepository.findAll(pageable);
    }

    @Override
    public int findAllCount() {
        return districtRepository.findAllCount();
    }

    @Override
    public List<DictDistrict> findAllList() {
        return districtRepository.findAllList();
    }

    @Override
    public DictDistrict findById(Long id) {
        return districtRepository.findById(id).get();
    }

    @Override
    public void saveOne(DictDistrict district) {
        districtRepository.save(district);
    }

    @Override
    public int deleteDistrict(int id) {
        return districtRepository.deleteDistrict(id);
    }

    @Override
    public int deleteAllDistrict(List<Long> districtList) {
        return districtRepository.deleteAllDistrict(districtList);
    }

    @Override
    public DictDistrict findOneByName(String districtName) {
        return districtRepository.findOneByName(districtName);
    }

    @Override
    public Page<DictDistrict> findAll(Specification<DictDistrict> dts, Pageable pageable) {
        return districtRepository.findAll(dts,pageable);
    }
}

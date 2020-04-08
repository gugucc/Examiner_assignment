package com.example.demo.service.Imp;


import com.example.demo.dto.Admin;
import com.example.demo.repository.AdminRepository;
import com.example.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;


    @Override
    public Admin login() {
        return null;
    }

    @Override
    public Admin findOneByPassword(String oldPassword) {
        return adminRepository.findOneByPassword(oldPassword);
    }
    @Override
    public void save(Admin admin) {
        adminRepository.save(admin);
    }
}

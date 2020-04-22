package com.example.demo.service.Imp;


import com.example.demo.dto.Admin;
import com.example.demo.dto.ExamSite;
import com.example.demo.repository.LoginAdminRepository;
import com.example.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginAdminRepository loginAdminRepository;


    @Override
    public Admin AdminLogin(String username) {
        return loginAdminRepository.adminLogin(username);
    }

    @Override
    public ExamSite examSiteLogin(String principal) {
        return loginAdminRepository.examSiteLogin(principal);
    }


}

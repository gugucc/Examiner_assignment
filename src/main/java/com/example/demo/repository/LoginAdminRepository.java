package com.example.demo.repository;


import com.example.demo.dto.Admin;
import com.example.demo.dto.ExamSite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface LoginAdminRepository extends JpaRepository<Admin,Long> {

    /**
     * 查询管理员
     * @param username
     * @return
     */
    @Query(value="from Admin where username=?1")
    Admin adminLogin(String username);


    /**
     * 查询学生
     * @param PrnTel
     * @return
     */
    @Query(value="from ExamSite where PrnTel=?1")
    ExamSite examsiteLogin(String PrnTel);


}

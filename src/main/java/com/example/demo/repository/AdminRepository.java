package com.example.demo.repository;


import com.example.demo.dto.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface AdminRepository extends JpaRepository<Admin,Long> {

    /**
     * 通过原密码查找管理员
     * @param oldPassword
     * @return
     */
    @Query(value = "from Admin where password = ?1")
    Admin findOneByPassword(String oldPassword);


    @Modifying
    @Query(value = "insert into Admin values(?1,?2)",nativeQuery = true)
    Admin insertAdmin(String username,String password);
}

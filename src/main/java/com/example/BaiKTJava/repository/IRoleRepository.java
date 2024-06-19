package com.example.BaiKTJava.repository;

import com.example.BaiKTJava.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends JpaRepository<Role,Long> {
    Role findRoleById(Long id);
}

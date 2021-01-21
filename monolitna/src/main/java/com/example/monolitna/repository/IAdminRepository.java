package com.example.monolitna.repository;

import com.example.monolitna.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAdminRepository extends JpaRepository<Admin, Long> {
    Admin findOneById(Long id);
}

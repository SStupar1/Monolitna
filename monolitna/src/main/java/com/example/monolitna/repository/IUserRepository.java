package com.example.monolitna.repository;

import com.example.monolitna.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    User findOneById(Long id);

    User findOneByUsername(String username);



}
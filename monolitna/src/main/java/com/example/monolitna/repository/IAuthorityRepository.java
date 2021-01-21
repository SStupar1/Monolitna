package com.example.monolitna.repository;

import com.example.monolitna.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuthorityRepository extends JpaRepository<Authority,Long> {

    Authority findOneByName(String s);

}

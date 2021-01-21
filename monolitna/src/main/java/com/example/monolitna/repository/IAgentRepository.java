package com.example.monolitna.repository;

import com.example.monolitna.entity.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAgentRepository extends JpaRepository<Agent, Long> {

    Agent findOneById(Long id);

}
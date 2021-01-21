package com.example.monolitna.repository;

import com.example.monolitna.entity.Pricelist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPricelistRepository extends JpaRepository<Pricelist, Long> {
    Pricelist findOneById(Long id);

}

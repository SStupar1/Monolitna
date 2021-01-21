package com.example.monolitna.repository;

import com.example.monolitna.entity.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDiscountRepository extends JpaRepository<Discount, Long> {

    Discount findOneById(Long id);
}

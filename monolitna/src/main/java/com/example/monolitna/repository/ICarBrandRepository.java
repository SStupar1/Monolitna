package com.example.monolitna.repository;

import com.example.monolitna.entity.CarBrand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICarBrandRepository extends JpaRepository<CarBrand, Long> {
    CarBrand findOneById(Long id);
}

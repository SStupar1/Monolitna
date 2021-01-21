package com.example.monolitna.repository;

import com.example.monolitna.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICarRepository extends JpaRepository<Car, Long> {
    Car findOneById(Long id);
}

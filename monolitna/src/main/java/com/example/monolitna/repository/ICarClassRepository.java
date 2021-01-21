package com.example.monolitna.repository;

import com.example.monolitna.entity.CarClass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICarClassRepository extends JpaRepository<CarClass, Long> {
    CarClass findOneById(Long id);
}

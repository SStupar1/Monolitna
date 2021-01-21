package com.example.monolitna.repository;

import com.example.monolitna.entity.Admin;
import com.example.monolitna.entity.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICarModelRepository  extends JpaRepository<CarModel, Long> {
    CarModel findOneById(Long carModelId);
}

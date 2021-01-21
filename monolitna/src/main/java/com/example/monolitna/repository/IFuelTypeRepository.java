package com.example.monolitna.repository;


import com.example.monolitna.entity.FuelType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFuelTypeRepository  extends JpaRepository<FuelType, Long> {
    FuelType findOneById(Long fuelTypeId);
}

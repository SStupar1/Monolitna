package com.example.monolitna.services;

import com.example.monolitna.dto.request.CreateCarRequest;
import com.example.monolitna.dto.request.UpdateCarRequest;
import com.example.monolitna.dto.response.CarResponse;

import java.util.List;

public interface ICarService {
    List<CarResponse> getAllCars();

    CarResponse getCarById(Long id);

    boolean updateCarById(Long id, UpdateCarRequest request);

    boolean deleteCarById(Long id);

    CarResponse createCar(CreateCarRequest request);
}

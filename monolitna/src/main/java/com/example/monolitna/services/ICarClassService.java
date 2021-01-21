package com.example.monolitna.services;

import com.example.monolitna.dto.request.CreateCarClassRequest;
import com.example.monolitna.dto.request.UpdateCarClassRequest;
import com.example.monolitna.dto.response.CarClassResponse;

import java.util.List;

public interface ICarClassService {
    boolean updateCarClassById(Long id, UpdateCarClassRequest request);

    List<CarClassResponse> getAllCarClasses();

    CarClassResponse getCarClassById(Long id);

    CarClassResponse createCarClass(CreateCarClassRequest request);

    boolean deleteCarClassById(Long id);
}

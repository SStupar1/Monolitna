package com.example.monolitna.services;

import com.example.monolitna.dto.request.CreateCarModelRequest;
import com.example.monolitna.dto.request.UpdateCarModelRequest;
import com.example.monolitna.dto.response.CarModelResponse;

import java.util.List;

public interface ICarModelService {
    List<CarModelResponse> getAllCarModels();

    boolean updateCarModelById(Long id, UpdateCarModelRequest request);

    CarModelResponse getCarModelById(Long id);

    boolean deleteCarModelById(Long id);

    CarModelResponse createCarModel(CreateCarModelRequest request);
}

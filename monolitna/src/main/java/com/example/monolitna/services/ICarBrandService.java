package com.example.monolitna.services;

import com.example.monolitna.dto.request.CreateCarBrandRequest;
import com.example.monolitna.dto.request.UpdateCarBrandRequest;
import com.example.monolitna.dto.response.CarBrandResponse;

import java.util.List;

public interface ICarBrandService {
    List<CarBrandResponse> getAllCarBrands();

    CarBrandResponse getCarBrandById(Long id);

    boolean updateCarBrandById(Long id, UpdateCarBrandRequest request);

    CarBrandResponse createCarBrand(CreateCarBrandRequest request);

    boolean deleteCarBrandById(Long id);
}

package com.example.monolitna.services;

import com.example.monolitna.dto.request.CreateFuelTypeRequest;
import com.example.monolitna.dto.request.UpdateFuelTypeRequest;
import com.example.monolitna.dto.response.FuelTypeResponse;

import java.util.List;

public interface IFuelTypeService {
    List<FuelTypeResponse> getAllFuelTypes();

    boolean updateFuelTypeById(Long id, UpdateFuelTypeRequest request);

    FuelTypeResponse getFuelTypeById(Long id);

    FuelTypeResponse createFuelType(CreateFuelTypeRequest request);

    boolean deleteFuelTypeById(Long id);
}

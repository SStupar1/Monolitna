package com.example.monolitna.services.impl;

import com.example.monolitna.dto.request.CreateFuelTypeRequest;
import com.example.monolitna.dto.request.UpdateFuelTypeRequest;
import com.example.monolitna.dto.response.FuelTypeResponse;
import com.example.monolitna.entity.FuelType;
import com.example.monolitna.repository.IFuelTypeRepository;
import com.example.monolitna.services.IFuelTypeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FuelTypeService implements IFuelTypeService {

    private final IFuelTypeRepository _fuelTypeRepository;

    public FuelTypeService(IFuelTypeRepository fuelTypeRepository) {
        _fuelTypeRepository = fuelTypeRepository;
    }

    public FuelTypeResponse mapFuelTypetoFuelTypeResponse(FuelType fuelType) {
        FuelTypeResponse response = new FuelTypeResponse();
        response.setId(fuelType.getId());
        response.setType(fuelType.getType());
        response.setTankCapacity(fuelType.getTankCapacity());
        return response;
    }

    @Override
    public List<FuelTypeResponse> getAllFuelTypes() {
        List<FuelType> fuelTypes = _fuelTypeRepository.findAll();
        return  fuelTypes.stream()
                .map(fuelType -> mapFuelTypetoFuelTypeResponse(fuelType))
                .collect(Collectors.toList());
    }

    @Override
    public boolean updateFuelTypeById(Long id, UpdateFuelTypeRequest request) {
        FuelType fuelType = _fuelTypeRepository.findOneById(id);
        if(fuelType != null) {
            fuelType.setType(request.getType());
            fuelType.setTankCapacity(request.getTankCapacity());
            _fuelTypeRepository.save(fuelType);
            return true;
        }
        return false;
    }

    @Override
    public FuelTypeResponse getFuelTypeById(Long id) {
        FuelType fuelType =  _fuelTypeRepository.findOneById(id);
        if(fuelType != null) {
            return mapFuelTypetoFuelTypeResponse(fuelType);
        }

        return null;
    }

    @Override
    public FuelTypeResponse createFuelType(CreateFuelTypeRequest request) {
        FuelType fuelType = new FuelType();
        fuelType.setType(request.getType());
        fuelType.setTankCapacity(request.getTankCapacity());
        FuelType savedFuelType = _fuelTypeRepository.save(fuelType);
        return mapFuelTypetoFuelTypeResponse(savedFuelType);
    }

    @Override
    public boolean deleteFuelTypeById(Long id) {
        FuelType fuelType = _fuelTypeRepository.findOneById(id);
        if(fuelType != null){
            _fuelTypeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

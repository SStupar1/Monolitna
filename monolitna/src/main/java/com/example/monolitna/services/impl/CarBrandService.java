package com.example.monolitna.services.impl;

import com.example.monolitna.dto.request.CreateCarBrandRequest;
import com.example.monolitna.dto.request.UpdateCarBrandRequest;
import com.example.monolitna.dto.response.CarBrandResponse;
import com.example.monolitna.entity.CarBrand;
import com.example.monolitna.repository.ICarBrandRepository;
import com.example.monolitna.services.ICarBrandService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarBrandService implements ICarBrandService {

    private final ICarBrandRepository _carBrandRepository;

    public CarBrandService(ICarBrandRepository carBrandRepository) {
        _carBrandRepository = carBrandRepository;
    }


    public CarBrandResponse mapCarBrandToCarBrandResponse(CarBrand carBrand) {
        CarBrandResponse response = new CarBrandResponse();
        response.setId(carBrand.getId());
        response.setName(carBrand.getName());
        response.setCountry(carBrand.getCountry());
        return response;
    }

    @Override
    public List<CarBrandResponse> getAllCarBrands() {
        List<CarBrand> carBrands = _carBrandRepository.findAll();
        return  carBrands.stream()
                .map(carBrand -> mapCarBrandToCarBrandResponse(carBrand))
                .collect(Collectors.toList());
    }

    @Override
    public CarBrandResponse getCarBrandById(Long id) {
        CarBrand carBrand = _carBrandRepository.findOneById(id);
        if(carBrand != null) {
            return mapCarBrandToCarBrandResponse(carBrand);
        }
        return null;
    }

    @Override
    public boolean updateCarBrandById(Long id, UpdateCarBrandRequest request) {
        CarBrand carBrand = _carBrandRepository.findOneById(id);
        if(carBrand != null) {
            carBrand.setCountry(request.getCountry());
            carBrand.setName(request.getName());
            _carBrandRepository.save(carBrand);
            return true;
        }
        return false;
    }

    @Override
    public CarBrandResponse createCarBrand(CreateCarBrandRequest request) {
        CarBrand carBrand = new CarBrand();
        carBrand.setName(request.getName());
        carBrand.setCountry(request.getCountry());
        CarBrand savedCarBrand = _carBrandRepository.save(carBrand);
        return mapCarBrandToCarBrandResponse(savedCarBrand);
    }

    @Override
    public boolean deleteCarBrandById(Long id) {
        CarBrand carBrand = _carBrandRepository.findOneById(id);
        if(carBrand != null){
            _carBrandRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

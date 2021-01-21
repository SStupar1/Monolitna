package com.example.monolitna.services.impl;

import com.example.monolitna.dto.request.CreateCarModelRequest;
import com.example.monolitna.dto.request.UpdateCarModelRequest;
import com.example.monolitna.dto.response.CarModelResponse;
import com.example.monolitna.entity.CarBrand;
import com.example.monolitna.entity.CarClass;
import com.example.monolitna.entity.CarModel;
import com.example.monolitna.repository.ICarBrandRepository;
import com.example.monolitna.repository.ICarClassRepository;
import com.example.monolitna.repository.ICarModelRepository;
import com.example.monolitna.services.ICarModelService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarModelService implements ICarModelService {
    private final ICarModelRepository  _carModelRepository;
    private final ICarBrandRepository _carBrandRepository;
    private final ICarClassRepository _carClassRepository;
    private final CarClassService _carCarClassService;
    private final CarBrandService _carBrandService;

    public CarModelService(ICarModelRepository carModelRepository, ICarBrandRepository carBrandRepository, ICarClassRepository carClassRepository, CarClassService carCarClassService, CarBrandService carBrandService){
        _carModelRepository = carModelRepository;
        _carBrandRepository = carBrandRepository;
        _carClassRepository = carClassRepository;
        _carCarClassService = carCarClassService;
        _carBrandService = carBrandService;
    }

    @Override
    public List<CarModelResponse> getAllCarModels() {
        List<CarModel> carModels = _carModelRepository.findAll();
        return  carModels.stream()
                .map(carModel -> mapCarModelToCarModelResponse(carModel))
                .collect(Collectors.toList());
    }

    @Override
    public boolean updateCarModelById(Long id, UpdateCarModelRequest request) {
        CarModel carModel = _carModelRepository.findOneById(id);
        if(carModel != null) {
            carModel.setName(request.getName());
            CarBrand carBrand = _carBrandRepository.findOneById(request.getCarBrandId());
            CarClass carClass = _carClassRepository.findOneById(request.getCarClassId());
            carModel.setCarBrand(carBrand);
            carModel.setCarClass(carClass);
            _carModelRepository.save(carModel);
            return true;
        }
        return false;
    }

    @Override
    public CarModelResponse createCarModel(CreateCarModelRequest request) {
        CarModel carModel = new CarModel();
        carModel.setName(request.getName());
        carModel.setCarBrand(_carBrandRepository.findOneById(request.getCarBrandId()));
        carModel.setCarClass(_carClassRepository.findOneById(request.getCarClassId()));
        CarModel savedCarModel = _carModelRepository.save(carModel);
        return mapCarModelToCarModelResponse(savedCarModel);
    }

    @Override
    public boolean deleteCarModelById(Long id) {
        CarModel carModel = _carModelRepository.findOneById(id);
        if(carModel != null){
            _carModelRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public CarModelResponse getCarModelById(Long id) {
        CarModel carModel = _carModelRepository.findOneById(id);
        if(carModel != null) {
            return mapCarModelToCarModelResponse(carModel);
        }
        return null;
    }


    public CarModelResponse mapCarModelToCarModelResponse(CarModel carModel) {
        CarModelResponse response = new CarModelResponse();
        response.setId(carModel.getId());
        response.setName(carModel.getName());
        response.setCarClass(_carCarClassService.mapCarClassToCarClassResponse(carModel.getCarClass()));
        response.setCarBrand(_carBrandService.mapCarBrandToCarBrandResponse(carModel.getCarBrand()));
        return response;
    }


}

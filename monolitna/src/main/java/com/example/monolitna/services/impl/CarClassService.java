package com.example.monolitna.services.impl;

import com.example.monolitna.dto.request.CreateCarClassRequest;
import com.example.monolitna.dto.request.UpdateCarClassRequest;
import com.example.monolitna.dto.response.CarClassResponse;
import com.example.monolitna.entity.CarClass;
import com.example.monolitna.repository.ICarClassRepository;
import com.example.monolitna.services.ICarClassService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarClassService implements ICarClassService {

    private final ICarClassRepository _carClassRepository;

    public CarClassService(ICarClassRepository carClassRepository){ _carClassRepository = carClassRepository; }

    public CarClassResponse mapCarClassToCarClassResponse(CarClass carClass) {
        CarClassResponse response = new CarClassResponse();
        response.setId(carClass.getId());
        response.setName(carClass.getName());
        return response;
    }

    @Override
    public boolean updateCarClassById(Long id, UpdateCarClassRequest request) {
        CarClass carClass = _carClassRepository.findOneById(id);
        if(carClass != null) {
            carClass.setName(request.getName());
            _carClassRepository.save(carClass);
            return true;
        }
        return false;
    }

    @Override
    public List<CarClassResponse> getAllCarClasses() {
        List<CarClass> carClasses = _carClassRepository.findAll();
        return  carClasses.stream()
                .map(carClass -> mapCarClassToCarClassResponse(carClass))
                .collect(Collectors.toList());
    }

    @Override
    public CarClassResponse getCarClassById(Long id) {
        CarClass carClass = _carClassRepository.findOneById(id);
        if(carClass != null) {
            return mapCarClassToCarClassResponse(carClass);
        }
        return null;
    }

    @Override
    public CarClassResponse createCarClass(CreateCarClassRequest request) {
        CarClass carClass = new CarClass();
        carClass.setName(request.getName());
        CarClass savedCarClass = _carClassRepository.save(carClass);
        return mapCarClassToCarClassResponse(savedCarClass);
    }

    @Override
    public boolean deleteCarClassById(Long id) {
        CarClass carClass = _carClassRepository.findOneById(id);
        if(carClass != null){
            _carClassRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

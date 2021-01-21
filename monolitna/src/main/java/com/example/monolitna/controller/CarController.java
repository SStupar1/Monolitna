package com.example.monolitna.controller;

import com.example.monolitna.dto.request.CreateCarRequest;
import com.example.monolitna.dto.request.UpdateCarRequest;
import com.example.monolitna.dto.response.CarResponse;
import com.example.monolitna.dto.response.TextResponse;
import com.example.monolitna.services.ICarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {

    private final ICarService _carService;

    public CarController(ICarService carService){
        _carService = carService;
    }

    @GetMapping()
    public List<CarResponse> getAllCars(){
        return _carService.getAllCars();
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getCar(@PathVariable("id") Long id){

        CarResponse carResponse = _carService.getCarById(id);
        if(carResponse != null){
            return new ResponseEntity<>(carResponse, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Car doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public  ResponseEntity<?> updateCar(@PathVariable("id") Long id, @RequestBody UpdateCarRequest request){
        TextResponse textResponse = new TextResponse();
        textResponse.setText("Updated !");
        if(_carService.updateCarById(id, request)){
            return new ResponseEntity<>(textResponse, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Car doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCar(@PathVariable("id") Long id){
        TextResponse textResponse = new TextResponse();
        textResponse.setText("Deleted !");
        if(_carService.deleteCarById(id)){
            return new ResponseEntity<>(textResponse, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Car doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public CarResponse createCar(@RequestBody CreateCarRequest request){
        return _carService.createCar(request);
    }
}


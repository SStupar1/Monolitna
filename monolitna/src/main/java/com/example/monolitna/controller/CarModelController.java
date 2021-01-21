package com.example.monolitna.controller;

import com.example.monolitna.dto.request.CreateCarModelRequest;
import com.example.monolitna.dto.request.UpdateCarModelRequest;
import com.example.monolitna.dto.response.CarModelResponse;
import com.example.monolitna.dto.response.TextResponse;
import com.example.monolitna.services.ICarModelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car-models")
public class CarModelController {

    private final ICarModelService _carModelService;

    public CarModelController(ICarModelService carModelService){
        _carModelService = carModelService;
    }

    @GetMapping()
    public List<CarModelResponse> getAllCarModels(){
        return _carModelService.getAllCarModels();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCarModel(@PathVariable("id") Long id, @RequestBody UpdateCarModelRequest request){
        TextResponse textResponse = new TextResponse();
        textResponse.setText("Updated !");
        if(_carModelService.updateCarModelById(id, request)){
            return new ResponseEntity<>(textResponse, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Car model doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCarModel(@PathVariable("id") Long id){
        CarModelResponse carModelResponse = _carModelService.getCarModelById(id);
        if(carModelResponse != null){
            return new ResponseEntity<>(carModelResponse, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Car model doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public CarModelResponse createCarModel(@RequestBody CreateCarModelRequest request){
        return _carModelService.createCarModel(request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCarModel(@PathVariable("id") Long id){
        TextResponse textResponse = new TextResponse();
        textResponse.setText("Deleted !");
        if(_carModelService.deleteCarModelById(id)){
            return new ResponseEntity<>(textResponse, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Car model doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }
}


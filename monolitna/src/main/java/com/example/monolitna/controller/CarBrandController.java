package com.example.monolitna.controller;

import com.example.monolitna.dto.request.CreateCarBrandRequest;
import com.example.monolitna.dto.request.UpdateCarBrandRequest;
import com.example.monolitna.dto.response.CarBrandResponse;
import com.example.monolitna.dto.response.TextResponse;
import com.example.monolitna.services.ICarBrandService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car-brands")
public class CarBrandController {

    private final ICarBrandService _carBrandService;

    public CarBrandController(ICarBrandService carBrandService){
        _carBrandService = carBrandService;
    }

    @GetMapping()
    public List<CarBrandResponse> getAllCarBrands(){
        return _carBrandService.getAllCarBrands();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCarBrand(@PathVariable("id") Long id){
        CarBrandResponse carBrandResponse = _carBrandService.getCarBrandById(id);
        if(carBrandResponse != null){
            return new ResponseEntity<>(carBrandResponse, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Car brand doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCarBrand(@PathVariable("id") Long id, @RequestBody UpdateCarBrandRequest request){
        TextResponse textResponse = new TextResponse();
        textResponse.setText("Updated !");
        if(_carBrandService.updateCarBrandById(id, request)){
            return new ResponseEntity<>(textResponse, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Car brand doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public CarBrandResponse createCarBrand(@RequestBody CreateCarBrandRequest request){
        return _carBrandService.createCarBrand(request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCarBrand(@PathVariable("id") Long id){
        TextResponse textResponse = new TextResponse();
        textResponse.setText("Deleted !");
        if(_carBrandService.deleteCarBrandById(id)){
            return new ResponseEntity<>(textResponse, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Car brand doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }
}


package com.example.monolitna.controller;

import com.example.monolitna.dto.request.CreateCarClassRequest;
import com.example.monolitna.dto.request.UpdateCarClassRequest;
import com.example.monolitna.dto.response.CarClassResponse;
import com.example.monolitna.dto.response.TextResponse;
import com.example.monolitna.services.ICarClassService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car-classes")
public class CarClassController {

    private final ICarClassService _carClassService;

    public CarClassController(ICarClassService carClassService){ _carClassService = carClassService; }

    @GetMapping()
    public List<CarClassResponse> getAllCarClasses(){
        return _carClassService.getAllCarClasses();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCarClass(@PathVariable("id") Long id, @RequestBody UpdateCarClassRequest request){
        TextResponse textResponse = new TextResponse();
        textResponse.setText("Updated !");
        if(_carClassService.updateCarClassById(id, request)){
            return new ResponseEntity<>(textResponse, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Car class doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCarClass(@PathVariable("id") Long id){
        CarClassResponse carClassResponse = _carClassService.getCarClassById(id);
        if(carClassResponse != null){
            return new ResponseEntity<>(carClassResponse, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Car class doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public CarClassResponse createCarClass(@RequestBody CreateCarClassRequest request){
        return _carClassService.createCarClass(request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCarClass(@PathVariable("id") Long id){
        TextResponse textResponse = new TextResponse();
        textResponse.setText("Deleted !");
        if(_carClassService.deleteCarClassById(id)){
            return new ResponseEntity<>(textResponse, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Car class doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }
}


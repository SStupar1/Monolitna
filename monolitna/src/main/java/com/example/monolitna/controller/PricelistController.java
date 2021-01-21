package com.example.monolitna.controller;

import com.example.monolitna.dto.request.AddDiscountRequest;
import com.example.monolitna.dto.request.CreatePricelistRequest;
import com.example.monolitna.dto.response.PricelistResponse;
import com.example.monolitna.services.IPricelistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pricelists")
public class PricelistController {
    private final IPricelistService _pricelistService;

    public PricelistController(IPricelistService pricelistService) {
        _pricelistService = pricelistService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPricelist(@PathVariable("id") Long id){

        PricelistResponse pricelistResponse = _pricelistService.getPricelistById(id);
        if(pricelistResponse != null){
            return new ResponseEntity<>(pricelistResponse, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Pricelist doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping()
    public List<PricelistResponse> getAllPricelists(){return _pricelistService.getAllPricelists();}

    @PostMapping()
    public PricelistResponse createPricelist(@RequestBody CreatePricelistRequest request){
        return _pricelistService.createPricelist(request);
    }

    @PostMapping("/discount")
    public PricelistResponse addDiscountToPricelist(@RequestBody AddDiscountRequest request){
        return _pricelistService.addDiscountToPricelist(request);
    }

}


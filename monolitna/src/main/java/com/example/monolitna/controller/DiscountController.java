package com.example.monolitna.controller;

import com.example.monolitna.dto.response.DiscountResponse;
import com.example.monolitna.services.IDiscountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/discounts")
public class DiscountController {
    private final IDiscountService _discountService;

    public DiscountController(IDiscountService discountService) {
        _discountService = discountService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDiscount(@PathVariable("id") Long id){

        DiscountResponse discountResponse = _discountService.getDiscountById(id);
        if(discountResponse != null){
            return new ResponseEntity<>(discountResponse, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Discount doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping()
    public List<DiscountResponse> getAllDiscounts(){
        return _discountService.getAllDiscounts();
    }




}
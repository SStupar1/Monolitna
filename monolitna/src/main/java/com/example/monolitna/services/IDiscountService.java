package com.example.monolitna.services;



import com.example.monolitna.dto.response.DiscountResponse;

import java.util.List;

public interface IDiscountService {

    DiscountResponse getDiscountById(Long id);

    List<DiscountResponse> getAllDiscounts();
}
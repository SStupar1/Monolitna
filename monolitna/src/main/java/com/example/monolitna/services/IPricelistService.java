package com.example.monolitna.services;

import com.example.monolitna.dto.request.AddDiscountRequest;
import com.example.monolitna.dto.request.CreatePricelistRequest;
import com.example.monolitna.dto.response.PricelistResponse;

import java.util.List;

public interface IPricelistService {
    PricelistResponse getPricelistById(Long id);

    PricelistResponse createPricelist(CreatePricelistRequest request);

    PricelistResponse addDiscountToPricelist(AddDiscountRequest request);

    List<PricelistResponse> getAllPricelists();
}

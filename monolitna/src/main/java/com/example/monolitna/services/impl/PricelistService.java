package com.example.monolitna.services.impl;

import com.example.monolitna.dto.request.AddDiscountRequest;
import com.example.monolitna.dto.request.CreatePricelistRequest;
import com.example.monolitna.dto.response.DiscountResponse;
import com.example.monolitna.dto.response.PricelistResponse;
import com.example.monolitna.entity.Discount;
import com.example.monolitna.entity.Pricelist;
import com.example.monolitna.repository.IDiscountRepository;
import com.example.monolitna.repository.IPricelistRepository;
import com.example.monolitna.services.IPricelistService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class PricelistService implements IPricelistService {

    private final IPricelistRepository _pricelistRepository;
    private final IDiscountRepository _discountRepository;

    public PricelistService(IPricelistRepository pricelistRepository, IDiscountRepository discountRepository) {
        _pricelistRepository = pricelistRepository;
        _discountRepository = discountRepository;
    }

    @Override
    public PricelistResponse getPricelistById(Long id) {
        Pricelist pricelist = _pricelistRepository.findOneById(id);
        if(pricelist != null) {
            return mapPricelistToPricelistResponse(pricelist);
        }
        return null;
    }

    @Override
    public PricelistResponse createPricelist(CreatePricelistRequest request) {
        Pricelist pricelist = new Pricelist();
        pricelist.setPriceCdw(request.getPriceCdw());
        pricelist.setPricePerDay(request.getPricePerDay());
        pricelist.setPricePerKilometer(request.getPricePerKilometer());
        Discount discount = _discountRepository.findOneById(request.getDiscountId());
        pricelist.setDiscount(discount);
        _pricelistRepository.save(pricelist);

        return mapPricelistToPricelistResponse(pricelist);
    }

    @Override
    public PricelistResponse addDiscountToPricelist(AddDiscountRequest request) {
        return null;
    }


    @Override
    public List<PricelistResponse> getAllPricelists() {
        List<Pricelist> pricelists = _pricelistRepository.findAll();
        return pricelists.stream()
                .map(pricelist -> mapPricelistToPricelistResponse(pricelist))
                .collect(Collectors.toList());
    }

    public PricelistResponse mapPricelistToPricelistResponse(Pricelist pricelist) {
        PricelistResponse pricelistResponse = new PricelistResponse();
        if(pricelist.getId() != null)
            pricelistResponse.setId(pricelist.getId());

        pricelistResponse.setPricePerDay(pricelist.getPricePerDay());
        pricelistResponse.setPriceCdw(pricelist.getPriceCdw());
        pricelistResponse.setPricePerKilometer(pricelist.getPricePerKilometer());
        pricelistResponse.setDiscount(mapDiscountToDiscountResponse(pricelist.getDiscount()));

        return pricelistResponse;
    }

    private DiscountResponse mapDiscountToDiscountResponse(Discount discount) {
        DiscountResponse discountResponse = new DiscountResponse();
        discountResponse.setId(discount.getId());
        discountResponse.setDiscount(discount.getDiscount());
        return discountResponse;
    }


}

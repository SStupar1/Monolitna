package com.example.monolitna.dto.request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePricelistRequest {

    private double pricePerDay;

    private double pricePerKilometer;

    private double priceCdw;

    private Long discountId;

}

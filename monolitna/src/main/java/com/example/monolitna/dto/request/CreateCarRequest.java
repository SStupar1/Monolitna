package com.example.monolitna.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCarRequest {

    private Long carModelId;

    private Long fuelTypeId;

    private Long gearshiftTypeId;

    private int kmTraveled;
}
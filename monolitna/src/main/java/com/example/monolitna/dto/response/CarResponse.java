package com.example.monolitna.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarResponse {

    private Long id;

    private int kmTraveled;

    private FuelTypeResponse fuelType;

    private GearshiftTypeResponse gearshiftType;

    private CarModelResponse carModel;

}

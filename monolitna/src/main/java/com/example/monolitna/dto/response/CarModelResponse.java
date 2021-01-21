package com.example.monolitna.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarModelResponse {

    private Long id;

    private String name;

    private CarClassResponse carClass;

    private CarBrandResponse carBrand;

}

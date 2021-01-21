package com.example.monolitna.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarBrandResponse {

    private Long id;

    private String name;

    private String country;

}

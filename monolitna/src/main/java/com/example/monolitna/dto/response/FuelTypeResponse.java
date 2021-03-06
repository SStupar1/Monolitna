package com.example.monolitna.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FuelTypeResponse {

    private Long id;

    private String type;

    private String tankCapacity;
}
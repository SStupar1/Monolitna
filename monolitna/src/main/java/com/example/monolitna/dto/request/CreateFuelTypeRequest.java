package com.example.monolitna.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateFuelTypeRequest {
    private String type; //benzin, dizel...

    private String tankCapacity;
}
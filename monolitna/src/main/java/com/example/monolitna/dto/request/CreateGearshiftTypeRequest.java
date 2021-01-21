package com.example.monolitna.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateGearshiftTypeRequest {
    private String type;

    private int numberOfGears; //broj zupcanika
}
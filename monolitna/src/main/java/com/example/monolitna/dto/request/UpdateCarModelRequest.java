package com.example.monolitna.dto.request;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCarModelRequest {
    private String name;
    private Long carClassId;
    private Long carBrandId;
}


package com.example.monolitna.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAdRequest {

    private boolean limitedDistance;
    private int limitedKm;
    private boolean cdw;
    private int seats;

}
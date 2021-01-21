package com.example.monolitna.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAdRequest {

    private Long publisherId;

    private Long carModelId;

    private Long fuelTypeId;

    private Long gearshiftTypeId;

    private int kmTraveled;

    private boolean limitedDistance;

    private int limitedKm;

    private boolean cdw;

    private int seats;

    private LocalDate creationDate;

    private boolean simpleUser;
}

package com.example.monolitna.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class ReservationRequest {
    private Long adId;

    private Long customerId;

    private String fromDateString;

    private String toDateString;

    private String fromTimeString;

    private String toTimeString;

    private boolean simpleUser;

}

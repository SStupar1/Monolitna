package com.example.monolitna.dto.response;

import com.example.monolitna.entity.Ad;
import com.example.monolitna.util.enums.ReservationStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
public class ReservationResponse {

    private Long id;

    private Ad ad;

    private LocalDate fromDate;

    private LocalDate toDate;

    private LocalTime fromTime;

    private LocalTime toTime;

    private boolean simpleUser;

    private ReservationStatus status;

    private Long customerId;

}

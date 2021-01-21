package com.example.monolitna.services;

import com.example.monolitna.dto.request.ReservationRequest;
import com.example.monolitna.dto.response.ReservationResponse;

import java.util.List;

public interface IReservationService {
    List<ReservationResponse> getAllAdReservations(Long id);

    List<ReservationResponse> getAllCustomerReservations(Long customerId, boolean simpleUser);

    ReservationResponse createReservation(ReservationRequest reservationRequest);

    ReservationResponse approveReservation(Long id);

    ReservationResponse denyReservation(Long id);

    List<ReservationResponse> getAllPublisherReservations(Long publisherId, boolean simpleUser);

    ReservationResponse getReservation(Long id);
}
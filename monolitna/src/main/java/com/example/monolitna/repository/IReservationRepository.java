package com.example.monolitna.repository;

import com.example.monolitna.entity.Reservation;
import com.example.monolitna.util.enums.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findAllByStatus(ReservationStatus pending);

    Reservation findOneById(Long id);
}

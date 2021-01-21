package com.example.monolitna.repository;

import com.example.monolitna.entity.Bundle;
import com.example.monolitna.util.enums.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IBundleRepository extends JpaRepository<Bundle, Long> {
    List<Bundle> findAllByStatus(ReservationStatus pending);

    Bundle findOneById(Long id);
}

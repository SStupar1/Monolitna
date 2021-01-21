package com.example.monolitna.repository;

import com.example.monolitna.entity.GearshiftType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IGearshiftTypeRepository extends JpaRepository<GearshiftType, Long> {
    GearshiftType findOneById(Long gearshiftTypeId);
}

package com.example.monolitna.services;

import com.example.monolitna.dto.request.CreateGearshiftTypeRequest;
import com.example.monolitna.dto.request.UpdateGearshiftRequest;
import com.example.monolitna.dto.response.GearshiftTypeResponse;

import java.util.List;

public interface IGearShiftTypeService {
    List<GearshiftTypeResponse> getAllGearshiftTypes();

    boolean updateGearshiftTypeById(Long id, UpdateGearshiftRequest request);

    GearshiftTypeResponse getGearshiftTypeById(Long id);

    GearshiftTypeResponse createGearshiftType(CreateGearshiftTypeRequest request);

    boolean deleteGearshiftTypeById(Long id);
}

package com.example.monolitna.services.impl;

import com.example.monolitna.dto.request.CreateGearshiftTypeRequest;
import com.example.monolitna.dto.request.UpdateGearshiftRequest;
import com.example.monolitna.dto.response.GearshiftTypeResponse;
import com.example.monolitna.entity.GearshiftType;
import com.example.monolitna.repository.IGearshiftTypeRepository;
import com.example.monolitna.services.IGearShiftTypeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GearshiftTypeService implements IGearShiftTypeService {

    private final IGearshiftTypeRepository _gearshiftTypeRepository;

    public GearshiftTypeService(IGearshiftTypeRepository gearshiftTypeRepository) {
        _gearshiftTypeRepository = gearshiftTypeRepository;
    }

    public GearshiftTypeResponse mapGearshiftTypeToGearshiftTypeRepository(GearshiftType gearshiftType) {
        GearshiftTypeResponse response = new GearshiftTypeResponse();
        response.setId(gearshiftType.getId());
        response.setType(gearshiftType.getType());
        response.setNumberOfGears(gearshiftType.getNumberOfGears());
        return response;
    }

    @Override
    public List<GearshiftTypeResponse> getAllGearshiftTypes() {
        List<GearshiftType> gearshiftTypes = _gearshiftTypeRepository.findAll();
        return  gearshiftTypes.stream()
                .map(gearshiftType -> mapGearshiftTypeToGearshiftTypeRepository(gearshiftType))
                .collect(Collectors.toList());
    }

    @Override
    public boolean updateGearshiftTypeById(Long id, UpdateGearshiftRequest request) {
        GearshiftType gearshiftType = _gearshiftTypeRepository.findOneById(id);
        if(gearshiftType != null) {
            gearshiftType.setType(request.getType());
            gearshiftType.setNumberOfGears(request.getNumberOfGears());
            _gearshiftTypeRepository.save(gearshiftType);
            return true;
        }
        return false;
    }

    @Override
    public GearshiftTypeResponse getGearshiftTypeById(Long id) {
        GearshiftType gearshiftType = _gearshiftTypeRepository.findOneById(id);
        if(gearshiftType != null) {
            return mapGearshiftTypeToGearshiftTypeRepository(gearshiftType);
        }
        return null;
    }

    @Override
    public GearshiftTypeResponse createGearshiftType(CreateGearshiftTypeRequest request) {
        GearshiftType gearshiftType = new GearshiftType();
        gearshiftType.setType(request.getType());
        gearshiftType.setNumberOfGears(request.getNumberOfGears());
        GearshiftType savedGearshiftType = _gearshiftTypeRepository.save(gearshiftType);
        return mapGearshiftTypeToGearshiftTypeRepository(savedGearshiftType);
    }

    @Override
    public boolean deleteGearshiftTypeById(Long id) {
        GearshiftType gearshiftType = _gearshiftTypeRepository.findOneById(id);
        if(gearshiftType != null){
            _gearshiftTypeRepository.deleteById(id);
            return true;
        }
        return false;
    }

}

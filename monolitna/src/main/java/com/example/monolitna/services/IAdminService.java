package com.example.monolitna.services;

import com.example.monolitna.dto.request.UpdateAdminRequest;
import com.example.monolitna.dto.response.AdminResponse;

public interface IAdminService {
    AdminResponse getAdminById(Long id);

    void updateAdminById(Long id, UpdateAdminRequest request);
}

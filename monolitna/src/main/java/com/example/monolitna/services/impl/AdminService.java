package com.example.monolitna.services.impl;

import com.example.monolitna.dto.request.UpdateAdminRequest;
import com.example.monolitna.dto.response.AdminResponse;
import com.example.monolitna.entity.Admin;
import com.example.monolitna.repository.IAdminRepository;
import com.example.monolitna.services.IAdminService;
import org.springframework.stereotype.Service;

@Service
public class AdminService implements IAdminService {

    private final IAdminRepository _adminRepository;


    public AdminService(IAdminRepository adminRepository) {
        _adminRepository = adminRepository;
    }
    @Override
    public AdminResponse getAdminById(Long id) {
        Admin admin = _adminRepository.findOneById(id);
        if(admin != null){
            return mapAdminToResponse(admin);
        }
        else{
            return null;
        }
    }

    @Override
    public void updateAdminById(Long id, UpdateAdminRequest request) {
        Admin admin = _adminRepository.findOneById(id);
        if(request.getFirstName() != null)
            admin.setFirstName(request.getFirstName());
        if(request.getLastName() != null)
            admin.setLastName(request.getLastName());

        _adminRepository.save(admin);
    }

    private AdminResponse mapAdminToResponse(Admin admin) {
        AdminResponse adminResponse = new AdminResponse();
        adminResponse.setFirstName(admin.getFirstName());
        adminResponse.setLastName(admin.getLastName());

        return adminResponse;
    }
}

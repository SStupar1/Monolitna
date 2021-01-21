package com.example.monolitna.controller;

import com.example.monolitna.dto.request.UpdateAdminRequest;
import com.example.monolitna.dto.response.AdminResponse;
import com.example.monolitna.services.IAdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/admins")

public class AdminController {
    private final IAdminService _adminService;

    public AdminController(IAdminService adminService){_adminService = adminService; }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAdmin(@PathVariable("id") Long id){
        AdminResponse adminResponse = _adminService.getAdminById(id);
        if(adminResponse != null){
            return new ResponseEntity<>(adminResponse, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Admin doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public void updateAdmin(@PathVariable("id")Long id, @RequestBody UpdateAdminRequest request){
        _adminService.updateAdminById(id, request);
    }

}

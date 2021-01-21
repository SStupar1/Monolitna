package com.example.monolitna.controller;

import com.example.monolitna.dto.request.GetIdRequest;
import com.example.monolitna.dto.request.UpdateSimpleUserRequest;
import com.example.monolitna.dto.response.SimpleUserResponse;
import com.example.monolitna.dto.response.TempResponse;
import com.example.monolitna.services.ISimpleUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/simple-users")
public class SimpleUserController {

    private final ISimpleUserService _simpleUserService;

    public SimpleUserController(ISimpleUserService simpleUserService) {
        _simpleUserService = simpleUserService;
    }


    @GetMapping()
    public List<SimpleUserResponse> getAllSimpleUsers(){
        return _simpleUserService.getAllSimpleUsers();
    }

    @PutMapping("/{id}")
    public void updateSimpleUser(@PathVariable("id")Long id, @RequestBody UpdateSimpleUserRequest request){
        _simpleUserService.updateSimpleUserById(id, request);

    }

    //admin odobrava
    @PutMapping("/approve")
    @PreAuthorize("hasAuthority('REGISTER')")
    public void approveRegistrationRequest(@RequestBody GetIdRequest request){
        _simpleUserService.approveRegistrationRequest(request);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSimpleUser(@PathVariable("id") Long id){
        SimpleUserResponse simpleUserResponse = _simpleUserService.getSimpleUserById(id);
        if(simpleUserResponse != null) {
            return new ResponseEntity<>(simpleUserResponse, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("User doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/registration-requests")
    @PreAuthorize("hasAuthority('REGISTER')")
    public List<SimpleUserResponse> getRegistrationRequests(){
        return _simpleUserService.getRegistrationRequests();
    }

    @PutMapping("/deny")
    @PreAuthorize("hasAuthority('REGISTER')")
    public void denyRegistrationRequest(@RequestBody GetIdRequest request){
        _simpleUserService.denyRegistrationRequest(request);
    }

    @PutMapping("/{id}/increase")
    void increaseNumOfAds(@PathVariable("id") Long id){
        _simpleUserService.increase(id);
    }

    //user potvrdjuje na mail-u
    @PutMapping("/confirm")
    public void confirmRegistrationRequest(@RequestBody GetIdRequest request){
        _simpleUserService.confirmRegistrationRequest(request);
    }

    @PutMapping("/block")
    public void blockSimpleUser(@RequestBody GetIdRequest request){
        _simpleUserService.blockSimpleUser(request);
    }

    @PutMapping("/activate")
    public void activateSimpleUser(@RequestBody GetIdRequest request){
        _simpleUserService.activateSimpleUser(request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSimpleUser(@PathVariable("id") Long id){
        TempResponse temp = new TempResponse();
        temp.setText("Deleted");
        if(_simpleUserService.deleteSimpleUserById(id)){
            return new ResponseEntity<>(temp, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Simple user doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/blocked")
    public List<SimpleUserResponse> getAllBlockedSimpleUsers(){
        return _simpleUserService.getAllBlockedSimpleUsers();
    }




}

package com.example.monolitna.controller;

import com.example.monolitna.dto.request.LoginRequest;
import com.example.monolitna.dto.request.RegisterAgentRequest;
import com.example.monolitna.dto.request.RegistrationRequest;
import com.example.monolitna.dto.response.LoginResponse;
import com.example.monolitna.dto.response.TempResponse;
import com.example.monolitna.dto.response.UserResponse;
import com.example.monolitna.security.TokenUtils;
import com.example.monolitna.services.IAuthService;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthController {
    private final TokenUtils _tokenUtils;

    private final IAuthService _authService;

    public AuthController(TokenUtils tokenUtils, IAuthService authService) {
        _tokenUtils = tokenUtils;
        _authService = authService;
    }

    @GetMapping("/verify")
    public String verify(@RequestHeader("Auth-Token") String token) throws NotFoundException {
        return _tokenUtils.getUsernameFromToken(token);
    }

    @GetMapping("/permission")
    public String getPermissions(@RequestHeader("Auth-Token") String token) throws NotFoundException {
        return _authService.getPermission(token);
    }

    @PutMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request){
       return _authService.login(request);
    }

    @PostMapping("/register-simple-user")
    public UserResponse registerSimpleUser(@RequestBody RegistrationRequest request){
        return _authService.registerSimpleUser(request);
    }

    @PostMapping("/register-agent")
    //@PreAuthorize("hasAuthority('REGISTER')")
    public ResponseEntity<?> registerAgent(@RequestBody RegisterAgentRequest request){
        TempResponse temp = new TempResponse();
        temp.setText("Registered agent");
        if(_authService.registerAgent(request)){
            return new ResponseEntity<>(temp, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("User already exists !", HttpStatus.NOT_FOUND);
        }
    }
}

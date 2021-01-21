package com.example.monolitna.services;

import com.example.monolitna.dto.request.LoginRequest;
import com.example.monolitna.dto.request.RegisterAgentRequest;
import com.example.monolitna.dto.request.RegistrationRequest;
import com.example.monolitna.dto.response.LoginResponse;
import com.example.monolitna.dto.response.UserResponse;

public interface IAuthService {

    String getPermission(String token);

    LoginResponse login(LoginRequest request);

    UserResponse registerSimpleUser(RegistrationRequest request);

    boolean registerAgent(RegisterAgentRequest request);
}

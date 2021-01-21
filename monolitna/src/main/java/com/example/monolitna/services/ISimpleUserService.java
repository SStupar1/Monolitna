package com.example.monolitna.services;

import com.example.monolitna.dto.request.GetIdRequest;
import com.example.monolitna.dto.request.UpdateSimpleUserRequest;
import com.example.monolitna.dto.response.SimpleUserResponse;

import java.util.List;

public interface ISimpleUserService {
    void approveRegistrationRequest(GetIdRequest request);

    void denyRegistrationRequest(GetIdRequest request);

    void confirmRegistrationRequest(GetIdRequest request);

    List<SimpleUserResponse> getRegistrationRequests();

    SimpleUserResponse getSimpleUserById(Long id);

    void increase(Long id);

    void blockSimpleUser(GetIdRequest request);

    void activateSimpleUser(GetIdRequest request);

    boolean deleteSimpleUserById(Long id);

    List<SimpleUserResponse> getAllBlockedSimpleUsers();

    void updateSimpleUserById(Long id, UpdateSimpleUserRequest request);

    List<SimpleUserResponse> getAllSimpleUsers();
}

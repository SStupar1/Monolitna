package com.example.monolitna.services.impl;

import com.example.monolitna.dto.request.GetIdRequest;
import com.example.monolitna.dto.request.UpdateSimpleUserRequest;
import com.example.monolitna.dto.response.SimpleUserResponse;
import com.example.monolitna.entity.SimpleUser;
import com.example.monolitna.entity.User;
import com.example.monolitna.repository.ISimpleUserRepository;
import com.example.monolitna.repository.IUserRepository;
import com.example.monolitna.services.IEmailService;
import com.example.monolitna.services.ISimpleUserService;
import com.example.monolitna.util.enums.RequestStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SimpleUserService implements ISimpleUserService {

    private final ISimpleUserRepository _simpleUserRepository;

    private final IEmailService _emailService;

    private final IUserRepository _userRepository;

    public SimpleUserService(ISimpleUserRepository simpleUserRepository, IEmailService emailService, IUserRepository userRepository) {
        _simpleUserRepository = simpleUserRepository;
        _emailService = emailService;
        _userRepository = userRepository;
    }


    @Override
    public void approveRegistrationRequest(GetIdRequest request) {
        SimpleUser simpleUser = _simpleUserRepository.findOneById(request.getId());
        simpleUser.setRequestStatus(RequestStatus.APPROVED);
        SimpleUser savedSimpleUser = _simpleUserRepository.save(simpleUser);

        _emailService.approveRegistrationMail(savedSimpleUser);
    }

    @Override
    public void denyRegistrationRequest(GetIdRequest request) {
        SimpleUser simpleUser = _simpleUserRepository.findOneById(request.getId());
        simpleUser.setRequestStatus(RequestStatus.DENIED);
        SimpleUser savedSimpleUser = _simpleUserRepository.save(simpleUser);

        _emailService.denyRegistrationMail(savedSimpleUser);
    }

    @Override
    public void confirmRegistrationRequest(GetIdRequest request) {
        SimpleUser simpleUser = _simpleUserRepository.findOneById(request.getId());
        simpleUser.setRequestStatus(RequestStatus.CONFIRMED);
        _simpleUserRepository.save(simpleUser);
    }

    @Override
    public List<SimpleUserResponse> getRegistrationRequests() {
        List<SimpleUser> simpleUsers = _simpleUserRepository.findAllByRequestStatus(RequestStatus.PENDING);
        List<SimpleUserResponse> simpleUserResponses = new ArrayList<>();
        for (SimpleUser simpleUser: simpleUsers) {
            SimpleUserResponse simpleUserResponse = mapSimpleUserToResponse(simpleUser);
            simpleUserResponses.add(simpleUserResponse);
        }
        return simpleUserResponses;
    }

    @Override
    public SimpleUserResponse getSimpleUserById(Long id) {
        SimpleUser simpleUser = _simpleUserRepository.findOneById(id);
        if(simpleUser != null) {
            return mapSimpleUserToResponse(simpleUser);
        } else {
            return null;
        }
    }

    @Override
    public void increase(Long id) {
        SimpleUser simpleUser = _simpleUserRepository.findOneById(id);
        simpleUser.setNumOfAds(simpleUser.getNumOfAds() + 1);
        _simpleUserRepository.save(simpleUser);
    }

    @Override
    public void blockSimpleUser(GetIdRequest request) {
        SimpleUser simpleUser = _simpleUserRepository.findOneById(request.getId());
        simpleUser.setDeleted(true);
        _simpleUserRepository.save(simpleUser);
    }

    @Override
    public void activateSimpleUser(GetIdRequest request) {
        SimpleUser simpleUser = _simpleUserRepository.findOneById(request.getId());
        simpleUser.setDeleted(false);
        _simpleUserRepository.save(simpleUser);
    }

    @Override
    public boolean deleteSimpleUserById(Long id) {
        SimpleUser simpleUser = _simpleUserRepository.findOneById(id);
        if(simpleUser != null){
            User user = _userRepository.findOneById(simpleUser.getUser().getId());
            user.setAuthorities(null);
            _userRepository.deleteById(user.getId());
            return true;
        }
        return false;
    }

    @Override
    public List<SimpleUserResponse> getAllBlockedSimpleUsers() {
        List<SimpleUser> simpleUsers = _simpleUserRepository.findAllByDeleted(true);
        List<SimpleUserResponse> simpleUserResponses = new ArrayList<>();
        for (SimpleUser simpleUser: simpleUsers) {
            SimpleUserResponse simpleUserResponse = mapSimpleUserToResponse(simpleUser);
            simpleUserResponses.add(simpleUserResponse);
        }
        return simpleUserResponses;
    }

    @Override
    public void updateSimpleUserById(Long id, UpdateSimpleUserRequest request) {
        SimpleUser simpleUser = _simpleUserRepository.findOneById(id);
        if(request.getAddress() != null)
            simpleUser.setAddress(request.getAddress());
        if(request.getFirstName() != null)
            simpleUser.setFirstName(request.getFirstName());
        if(request.getLastName() != null)
            simpleUser.setLastName(request.getLastName());
        if(request.getSsn() != null)
            simpleUser.setSsn(request.getSsn());

        _simpleUserRepository.save(simpleUser);
    }

    @Override
    public List<SimpleUserResponse> getAllSimpleUsers() {
        List<SimpleUser> simpleUsers = _simpleUserRepository.findAllByDeleted(false);
        List<SimpleUserResponse> simpleUserResponses = new ArrayList<>();
        for (SimpleUser simpleUser: simpleUsers) {
            SimpleUserResponse simpleUserResponse = mapSimpleUserToResponse(simpleUser);
            simpleUserResponses.add(simpleUserResponse);
        }
        return simpleUserResponses;
    }

    private SimpleUserResponse mapSimpleUserToResponse(SimpleUser simpleUser) {
        SimpleUserResponse simpleUserResponse = new SimpleUserResponse();
        simpleUserResponse.setId(simpleUser.getId());
        simpleUserResponse.setAddress(simpleUser.getAddress());
        simpleUserResponse.setFirstName(simpleUser.getFirstName());
        simpleUserResponse.setLastName(simpleUser.getLastName());
        simpleUserResponse.setSsn(simpleUser.getSsn());
        simpleUserResponse.setUsername(simpleUser.getUser().getUsername());
        simpleUserResponse.setUserRole(simpleUser.getUser().getUserRole().toString());
        simpleUserResponse.setNumOfAds(simpleUser.getNumOfAds());
        return simpleUserResponse;
    }
}

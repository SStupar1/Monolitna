package com.example.monolitna.services;


import com.example.monolitna.entity.SimpleUser;

public interface IEmailService {

    void approveRegistrationMail(SimpleUser simpleUser);

    void denyRegistrationMail(SimpleUser simpleUser);
}

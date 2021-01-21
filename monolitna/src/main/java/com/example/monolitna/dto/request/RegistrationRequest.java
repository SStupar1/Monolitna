package com.example.monolitna.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationRequest {

    private String username;
    private String password;
    private String rePassword;
    private String firstName;
    private String lastName;
    private String ssn;
    private String address;

}

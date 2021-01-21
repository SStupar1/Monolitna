package com.example.monolitna.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateSimpleUserRequest {

    private String firstName;
    private String lastName;
    private String address;
    private String ssn;

}

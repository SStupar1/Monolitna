package com.example.monolitna.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgentResponse {

    private Long id;
    private String username;
    private String userRole;
    private String name;
    private String pib;
    private LocalDate dateFounded;
    private String bankAccountNumber;
    private String address;
   // private Long simpleUserId;

}
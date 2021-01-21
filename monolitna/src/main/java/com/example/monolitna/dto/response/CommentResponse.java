package com.example.monolitna.dto.response;

import com.example.monolitna.util.enums.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponse {

    private Long id;

    private String content;

    private Long customerId;

    private boolean simpleUser;

    private AdResponse ad;

    private RequestStatus status;
}
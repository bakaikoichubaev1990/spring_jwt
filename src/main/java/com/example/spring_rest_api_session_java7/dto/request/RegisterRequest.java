package com.example.spring_rest_api_session_java7.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
public class RegisterRequest {

    private String email;
    private String password;
    private String firstName;
}

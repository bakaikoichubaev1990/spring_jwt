package com.example.spring_rest_api_session_java7.dto.instructor;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InstructorRequest {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String specialization;
}

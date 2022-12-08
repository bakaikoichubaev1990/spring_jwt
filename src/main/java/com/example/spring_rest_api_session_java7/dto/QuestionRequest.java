package com.example.spring_rest_api_session_java7.dto;

import com.example.spring_rest_api_session_java7.entities.Option;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class QuestionRequest {

    private String name;
    private String text;
    private int duration;
    private int numberOfRespondents;
    private List<Option>options;
}

package com.example.spring_rest_api_session_java7.converter;

import com.example.spring_rest_api_session_java7.dto.QuestionResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * author: Ulansky
 */
@Getter
@Setter
public class QuestionConverterView {
    private List<QuestionResponse> questionResponseList;
}

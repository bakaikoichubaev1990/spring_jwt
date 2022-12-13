package com.example.spring_rest_api_session_java7.service;

import com.example.spring_rest_api_session_java7.dto.request.QuestionRequest;
import com.example.spring_rest_api_session_java7.dto.response.QuestionResponse;

import java.util.List;

public interface QuestionService {

    QuestionResponse saveQuestion(QuestionRequest questionRequest);

    List<QuestionResponse> findAllQuestion();

    QuestionResponse findByIdQuestion(Long id);

    QuestionResponse deleteByIdQuestion(Long id);

    QuestionResponse updateQuestion(Long id,QuestionRequest questionRequest);


}

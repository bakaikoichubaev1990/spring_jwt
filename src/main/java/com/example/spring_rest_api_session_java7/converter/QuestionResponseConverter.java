package com.example.spring_rest_api_session_java7.converter;

import com.example.spring_rest_api_session_java7.dto.QuestionResponse;
import com.example.spring_rest_api_session_java7.entities.Question;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class QuestionResponseConverter {

    public QuestionResponse viewQuestion(Question question){
        if (question == null){
            return null;
        }
        QuestionResponse questionResponse = new QuestionResponse();
        questionResponse.setId(question.getId());
        questionResponse.setName(question.getName());
        questionResponse.setText(question.getText());
        questionResponse.setDuration(question.getDuration());
        questionResponse.setNumberOfRespondents(question.getNumberOfRespondents());
        questionResponse.setOptions(question.getOptions());
        return questionResponse;
    }
    public List<QuestionResponse> view(List<Question> questions){
        List<QuestionResponse> questionResponses = new ArrayList<>();
        for (Question question:questions) {
            questionResponses.add(viewQuestion(question));
        }
        return questionResponses;
    }
}

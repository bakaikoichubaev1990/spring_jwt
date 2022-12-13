package com.example.spring_rest_api_session_java7.converter;

import com.example.spring_rest_api_session_java7.dto.request.QuestionRequest;
import com.example.spring_rest_api_session_java7.entities.Option;
import com.example.spring_rest_api_session_java7.entities.Question;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QuestionRequestConverter {

    public Question create(QuestionRequest questionRequest) {
        if (questionRequest == null) {
            return null;
        }
        Question question = new Question();
        question.setName(questionRequest.getName());
        question.setText(questionRequest.getText());
        question.setDuration(questionRequest.getDuration());
        question.setNumberOfRespondents(questionRequest.getNumberOfRespondents());
        question.setOptions(questionRequest.getOptions());
        return question;
    }

    public void update(Question question, QuestionRequest questionRequest) {
        question.setName(questionRequest.getName());
        question.setText(questionRequest.getText());
        question.setDuration(questionRequest.getDuration());
        question.setNumberOfRespondents(questionRequest.getNumberOfRespondents());
        List<Option> options = question.getOptions();
        List<Option> optionList = questionRequest.getOptions();
        for (Option o1:options) {
            for (Option o2:optionList) {
                o1.setName(o2.getName());
                o1.setFile(o2.getFile());
                o1.setCorrect(o2.isCorrect());
            }
        }
    }
}

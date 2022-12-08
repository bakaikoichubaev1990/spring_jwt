package com.example.spring_rest_api_session_java7.api;

import com.example.spring_rest_api_session_java7.converter.QuestionConverterView;
import com.example.spring_rest_api_session_java7.dto.QuestionRequest;
import com.example.spring_rest_api_session_java7.dto.QuestionResponse;
import com.example.spring_rest_api_session_java7.service.QuestionService;
import com.example.spring_rest_api_session_java7.service.impl.QuestionServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class QuestionController {

    private final QuestionService questionService;
    private final QuestionServiceImpl service;

    @PostMapping("/save")
    public QuestionResponse save(@RequestBody QuestionRequest questionRequest){
        return questionService.saveQuestion(questionRequest);
    }
    @GetMapping("/all")
    public List<QuestionResponse> findAll(){
        return questionService.findAllQuestion();
    }
    @GetMapping("/{id}")
    public QuestionResponse findById(@PathVariable Long id){
        return questionService.findByIdQuestion(id);
    }
    @DeleteMapping("/{id}")
    public QuestionResponse deleteById(@PathVariable Long id){
        return questionService.deleteByIdQuestion(id);
    }
    @PutMapping("/{id}")
    public QuestionResponse update(@PathVariable Long id,
                                   @RequestBody QuestionRequest questionRequest){
        return questionService.updateQuestion(id,questionRequest);
    }
    @GetMapping("/search")
    public QuestionConverterView getAllQuestionsPage(@RequestParam(name = "text",required = false) String text,
                                                     @RequestParam int page,
                                                     @RequestParam int size){
        return service.getAllQuestions(text, page, size);
    }

}

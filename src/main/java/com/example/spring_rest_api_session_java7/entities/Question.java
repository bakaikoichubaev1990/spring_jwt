package com.example.spring_rest_api_session_java7.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "questions")
@Getter
@Setter
@NoArgsConstructor
public class Question {

    @Id
    @SequenceGenerator(name = "question_gen",sequenceName = "question_seq",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "question_gen")
    private Long id;
    private String name;
    private String text;
    private int duration;
    private int numberOfRespondents;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Option>options;

}

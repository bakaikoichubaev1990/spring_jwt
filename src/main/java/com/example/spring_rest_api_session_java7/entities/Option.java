package com.example.spring_rest_api_session_java7.entities;

import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "options")
@Getter
@Setter
@NoArgsConstructor
public class Option {

    @Id
    @SequenceGenerator(name = "option_gen", sequenceName = "option_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "option_gen")
    private Long id;
    private String name;
    private String file;
    private boolean isCorrect = false;
}

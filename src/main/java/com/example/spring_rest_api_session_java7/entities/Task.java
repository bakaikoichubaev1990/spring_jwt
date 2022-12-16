package com.example.spring_rest_api_session_java7.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

import static javax.persistence.CascadeType.*;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor

public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_gen")
    @SequenceGenerator(name = "task_gen", sequenceName = "task_gen", allocationSize = 1)
    private Long id;

    @Column(length = 100000,name = "task_name")
    private String taskName;
    @Column(length = 100000,name = "task_text")
    private String taskText;
    @Column(length = 100000,name = "task_deadline")
    private LocalDate deadline;

    @ManyToOne(cascade = {MERGE,DETACH,PERSIST,REFRESH},fetch = FetchType.EAGER)
    private Lesson lesson;
}

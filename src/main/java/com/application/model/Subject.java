package com.application.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity(name = "subjects")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title", nullable = false, unique = true, length = 128)
    private String title;

    @OneToMany(mappedBy = "subject")
    private Set<StudentSubject> studentSubjects;
}

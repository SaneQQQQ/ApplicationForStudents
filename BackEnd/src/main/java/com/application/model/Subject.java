package com.application.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Entity(name = "subjects")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank
    @Column(name = "title", nullable = false, unique = true, length = 128)
    private String title;

    @Setter(AccessLevel.NONE)
    @Formula("(SELECT COUNT(s.student_id) FROM students_subjects AS s WHERE s.subject_id = id)")
    private int countOfStudents;
}

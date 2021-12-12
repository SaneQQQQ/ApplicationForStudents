package com.application.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@Setter
@IdClass(StudentSubjectId.class)
@Entity(name = "students_subjects")
public class StudentSubject {

    @Id
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @Id
    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @Min(0)
    @Max(100)
    @Column(name = "mark")
    private Integer mark;
}

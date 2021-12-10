package com.application.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Entity(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 128)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 128)
    private String lastName;

    @Email
    @Column(name = "email", nullable = false, unique = true, length = 128)
    private String email;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;

    @JsonIgnore
    @OneToMany(mappedBy = "student")
    private Set<StudentSubject> studentSubjects;

    @Formula("(SELECT AVG(s.mark) FROM students_subjects AS s WHERE s.student_id = id)")
    private double averageRank;
}

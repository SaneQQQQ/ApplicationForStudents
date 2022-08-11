package com.application.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Entity(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank
    @Column(name = "first_name", nullable = false, length = 128)
    private String firstName;

    @NotBlank
    @Column(name = "last_name", nullable = false, length = 128)
    private String lastName;

    @Email
    @Column(name = "email", nullable = false, unique = true, length = 128)
    private String email;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;

    @Setter(AccessLevel.NONE)
    @Formula("(SELECT AVG(s.mark) FROM students_subjects AS s WHERE s.student_id = id)")
    private BigDecimal averageRank;

    @OneToMany(mappedBy = "student", cascade = CascadeType.REMOVE)
    private Set<StudentSubject> marks;
}

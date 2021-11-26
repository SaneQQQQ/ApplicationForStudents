package com.application.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    @Pattern(regexp = "", message = "Invalid Firstname. Firstname should be ...")
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @Pattern(regexp = "", message = "Invalid Lastname. Lastname should be ...")
    private String lastName;

    @NotNull
    @Email(message = "Invalid Email!")
    private String email;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;

    @ToString.Exclude
    @OneToMany(mappedBy = "student")
    private Set<StudentSubject> studentSubjects;

//    @Transient
//    private double averageRank;

}

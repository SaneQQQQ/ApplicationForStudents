package com.application.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity(name = "groups")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Pattern(regexp = "(^[0-9]{3})([\\-][A-Z]$){0,1}", message = "Invalid Title. Title must contains three digits or three digits with dash and single upper case alphabet letter")
    @Column(name = "title", nullable = false, unique = true, length = 5)
    private String title;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
}

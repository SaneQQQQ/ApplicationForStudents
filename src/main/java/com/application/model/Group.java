package com.application.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Pattern(regexp = "[A-Z0-9\\-]{1,16}", message = "Invalid Title. Title must be between 1-16 upper case characters or numbers, inclusive dashes")
    @Column(name = "title", nullable = false, unique = true, length = 16)
    private String title;

    @JsonIgnore
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

}

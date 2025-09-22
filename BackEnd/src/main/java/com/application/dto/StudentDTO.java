package com.application.dto;

import jakarta.validation.constraints.Digits;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    @Digits(integer = 3, fraction = 1)
    private double averageRank;
}

package com.application.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Digits;

@Getter
@Setter
public class FullStudentDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private GroupDTO group;

    @Digits(integer = 3, fraction = 1)
    private double averageRank;
}

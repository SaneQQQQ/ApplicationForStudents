package com.application.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Digits;
import java.math.BigDecimal;

@Getter
@Setter
public class StudentDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    @Digits(integer = 3, fraction = 1)
    private BigDecimal averageRank;

}

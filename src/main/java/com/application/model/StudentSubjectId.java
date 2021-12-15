package com.application.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
public class StudentSubjectId implements Serializable {

    private Student student;
    private Subject subject;
}

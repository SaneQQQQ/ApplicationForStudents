package com.application.model;

import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
public class StudentSubjectId implements Serializable {
    private Student student;
    private Subject subject;
}

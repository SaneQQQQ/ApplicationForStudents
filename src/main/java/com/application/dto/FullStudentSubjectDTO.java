package com.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FullStudentSubjectDTO {

    private StudentDTO student;

    private SubjectDTO subject;

    private int mark;
}

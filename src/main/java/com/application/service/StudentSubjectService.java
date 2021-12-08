package com.application.service;

import com.application.model.StudentSubject;
import com.application.model.StudentSubjectId;

import java.util.List;

public interface StudentSubjectService {

    StudentSubject create(StudentSubject studentSubject);

    StudentSubject read(StudentSubjectId id);

    boolean update(StudentSubject studentSubject);

    boolean delete(StudentSubjectId id);

    List<StudentSubject> readAll();
}

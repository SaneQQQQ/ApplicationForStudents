package com.application.service;

import com.application.model.StudentSubject;

import java.util.List;

public interface StudentSubjectService {

    StudentSubject create(StudentSubject studentSubject);

    StudentSubject read(Long studentId, Long subjectId);

    StudentSubject update(StudentSubject studentSubject);

    boolean delete(Long studentId, Long subjectId);

    List<StudentSubject> readAll();

    List<StudentSubject> readAllByStudentId(Long id);
}

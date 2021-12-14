package com.application.service;

import com.application.model.StudentSubject;
import com.application.model.StudentSubjectId;

import java.util.List;

public interface StudentSubjectService {

    StudentSubjectId create(StudentSubject studentSubject);

    StudentSubject read(Long studentId, Long subjectId);

    List<StudentSubject> readAllByStudentId(Long id);

    StudentSubject update(StudentSubject studentSubject);

    boolean delete(Long studentId, Long subjectId);
}

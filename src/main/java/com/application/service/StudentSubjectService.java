package com.application.service;

import com.application.dto.FullStudentSubjectDTO;
import com.application.dto.StudentSubjectDTO;

import java.util.List;

public interface StudentSubjectService {

    FullStudentSubjectDTO create(FullStudentSubjectDTO studentSubject);

    StudentSubject read(Long studentId, Long subjectId);

    List<StudentSubject> readAllByStudentId(Long id);

    StudentSubject update(StudentSubject studentSubject);

    void delete(Long studentId, Long subjectId);
}

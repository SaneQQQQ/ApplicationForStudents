package com.application.service;

import com.application.dto.FullStudentSubjectDTO;
import com.application.dto.StudentSubjectDTO;

import java.util.List;

public interface StudentSubjectService {

    FullStudentSubjectDTO create(FullStudentSubjectDTO studentSubject);

    FullStudentSubjectDTO read(Long studentId, Long subjectId);

    List<StudentSubjectDTO> readAllByStudentId(Long id);

    FullStudentSubjectDTO update(FullStudentSubjectDTO studentSubject);

    void delete(Long studentId, Long subjectId);
}

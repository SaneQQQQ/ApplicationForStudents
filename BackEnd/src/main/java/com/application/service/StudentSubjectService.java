package com.application.service;

import com.application.dto.FullStudentSubjectDTO;
import com.application.dto.StudentSubjectDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface StudentSubjectService {

    FullStudentSubjectDTO create(FullStudentSubjectDTO studentSubject);

    FullStudentSubjectDTO read(Long studentId, Long subjectId);

    Page<StudentSubjectDTO> readAllByStudentId(Long id, PageRequest pageRequest);

    FullStudentSubjectDTO update(FullStudentSubjectDTO studentSubject);

    void delete(Long studentId, Long subjectId);
}

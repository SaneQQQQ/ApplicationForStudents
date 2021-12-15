package com.application.service;

import com.application.dto.FullStudentDTO;
import com.application.dto.StudentDTO;

import java.util.List;

public interface StudentService {

    FullStudentDTO create(FullStudentDTO student);

    FullStudentDTO read(Long id);

    List<FullStudentDTO> readAll();

    List<StudentDTO> readAllByGroupId(Long id);

    FullStudentDTO update(FullStudentDTO student);

    void delete(Long id);
}

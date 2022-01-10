package com.application.service;

import com.application.dto.FullStudentDTO;
import com.application.dto.StudentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface StudentService {

    FullStudentDTO create(FullStudentDTO student);

    FullStudentDTO read(Long id);

    Page<FullStudentDTO> readAll(PageRequest pageRequest);

    Page<StudentDTO> readAllByGroupId(Long id, PageRequest pageRequest);

    FullStudentDTO update(FullStudentDTO student);

    void delete(Long id);
}

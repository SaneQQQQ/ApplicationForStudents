package com.application.dao;

import com.application.model.StudentSubject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudentSubjectDAO extends BaseDAO<StudentSubject> {

    Page<StudentSubject> readAllByStudentId(Long id, Pageable pageable);
}

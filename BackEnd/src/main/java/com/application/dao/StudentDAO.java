package com.application.dao;

import com.application.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudentDAO extends BaseDAO<Student> {

    Page<Student> readAll(Pageable pageable);

    Page<Student> readAllByGroupId(Long id, Pageable pageable);
}

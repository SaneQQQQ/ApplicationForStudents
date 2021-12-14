package com.application.service;

import com.application.model.Student;

import java.util.List;

public interface StudentService {

    Long create(Student student);

    Student read(Long id);

    List<Student> readAll();

    List<Student> readAllByGroupId(Long id);

    Student update(Student student);

    boolean delete(Long id);
}

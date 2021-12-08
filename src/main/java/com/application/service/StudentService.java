package com.application.service;

import com.application.model.Student;

import java.util.List;

public interface StudentService {

    Student create(Student student);

    Student read(Long id);

    boolean update(Student student);

    boolean delete(Long id);

    List<Student> readAll();
}

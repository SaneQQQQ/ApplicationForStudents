package com.application.service;

import com.application.model.Student;

import java.util.List;

public interface StudentService {

    FullStudentDTO create(FullStudentDTO student);

    Student read(Long id);

    List<Student> readAll();

    List<Student> readAllByGroupId(Long id);

    Student update(Student student);

    void delete(Long id);
}

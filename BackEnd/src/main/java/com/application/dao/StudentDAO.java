package com.application.dao;

import com.application.model.Student;

import java.util.List;

public interface StudentDAO extends BaseDAO<Student> {

    List<Student> readAllByGroupId(Long id);
}

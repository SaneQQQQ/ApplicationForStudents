package com.application.dao;

import com.application.model.Student;

import java.util.List;

public interface StudentDAO extends BaseOperationsDAO<Student>{

    List<Student> readAllByGroupId(Long id);
}

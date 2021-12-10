package com.application.dao;

import com.application.model.StudentSubject;

import java.util.List;

public interface StudentSubjectDAO extends BaseOperationsDAO<StudentSubject> {

    List<StudentSubject> readAllByStudentId(Long id);
}

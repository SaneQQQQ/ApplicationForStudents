package com.application.service;

import com.application.model.Subject;

import java.util.List;

public interface SubjectService {

    Long create(Subject subject);

    Subject read(Long id);

    List<Subject> readAll();

    Subject update(Subject subject);

    boolean delete(Long id);
}

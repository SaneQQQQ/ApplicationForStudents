package com.application.service;

import com.application.model.Subject;

import java.util.List;

public interface SubjectService {

    Subject create(Subject subject);

    Subject read(Long id);

    boolean update(Subject subject);

    boolean delete(Long id);

    List<Subject> readAll();
}

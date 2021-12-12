package com.application.service;

import com.application.model.Subject;

import java.util.List;

public interface SubjectService {

    Subject create(Subject subject);

    Subject read(Long id);

    Subject update(Subject subject);

    boolean delete(Long id);

    List<Subject> readAll();
}

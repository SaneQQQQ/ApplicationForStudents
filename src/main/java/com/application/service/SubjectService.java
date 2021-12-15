package com.application.service;

import com.application.model.Subject;

import java.util.List;

public interface SubjectService {

    SubjectDTO create(SubjectDTO subject);

    Subject read(Long id);

    List<Subject> readAll();

    Subject update(Subject subject);

    void delete(Long id);
}

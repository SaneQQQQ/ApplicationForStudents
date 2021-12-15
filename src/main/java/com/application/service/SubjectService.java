package com.application.service;

import com.application.dto.SubjectDTO;

import java.util.List;

public interface SubjectService {

    SubjectDTO create(SubjectDTO subject);

    SubjectDTO read(Long id);

    List<SubjectDTO> readAll();

    SubjectDTO update(SubjectDTO subject);

    void delete(Long id);
}

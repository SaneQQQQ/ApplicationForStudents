package com.application.service;

import com.application.dto.SubjectDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface SubjectService {

    SubjectDTO create(SubjectDTO subject);

    SubjectDTO read(Long id);

    Page<SubjectDTO> readAll(PageRequest pageRequest);

    SubjectDTO update(SubjectDTO subject);

    void delete(Long id);
}

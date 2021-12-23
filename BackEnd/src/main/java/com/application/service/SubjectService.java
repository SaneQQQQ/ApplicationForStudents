package com.application.service;

import com.application.dto.SubjectDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface SubjectService {

    SubjectDTO create(SubjectDTO subject);

    SubjectDTO read(Long id);

    List<SubjectDTO> readAll();

    Page<SubjectDTO> readAllSortedByTitle(PageRequest pageRequest);

    SubjectDTO update(SubjectDTO subject);

    void delete(Long id);
}

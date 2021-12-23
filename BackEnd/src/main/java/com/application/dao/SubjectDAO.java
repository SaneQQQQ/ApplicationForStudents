package com.application.dao;

import com.application.model.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SubjectDAO extends BaseDAO<Subject> {

    Page<Subject> readAllSortedByTitle(Pageable pageable);
}

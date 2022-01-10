package com.application.dao;

import com.application.model.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GroupDAO extends BaseDAO<Group> {

    Page<Group> readAll(Pageable pageable);
}

package com.application.service;

import com.application.model.Group;

import java.util.List;

public interface GroupService {

    Long create(Group group);

    Group read(Long id);

    List<Group> readAll();

    Group update(Group group);

    boolean delete(Long id);
}

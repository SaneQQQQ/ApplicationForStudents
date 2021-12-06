package com.application.service;

import com.application.model.Group;

import java.util.List;

public interface GroupService {
    void create(Group group);

    Group read(Long id);

    void update(Group group);

    void delete(Long id);

    List<Group> getAll();
}

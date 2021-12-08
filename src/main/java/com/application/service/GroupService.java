package com.application.service;

import com.application.model.Group;

import java.util.List;

public interface GroupService {

    Group create(Group group);

    Group read(Long id);

    boolean update(Group group);

    boolean delete(Long id);

    List<Group> readAll();
}

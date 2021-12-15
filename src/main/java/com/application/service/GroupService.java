package com.application.service;

import com.application.model.Group;

import java.util.List;

public interface GroupService {

    GroupDTO create(GroupDTO group);

    Group read(Long id);

    List<Group> readAll();

    Group update(Group group);

    void delete(Long id);
}

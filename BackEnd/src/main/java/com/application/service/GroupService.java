package com.application.service;

import com.application.dto.GroupDTO;

import java.util.List;

public interface GroupService {

    GroupDTO create(GroupDTO group);

    GroupDTO read(Long id);

    List<GroupDTO> readAll();

    GroupDTO update(GroupDTO group);

    void delete(Long id);
}

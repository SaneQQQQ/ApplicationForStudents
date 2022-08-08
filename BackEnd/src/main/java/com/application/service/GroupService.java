package com.application.service;

import com.application.dto.FullGroupDTO;
import com.application.dto.GroupDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface GroupService {

    GroupDTO create(GroupDTO group);

    GroupDTO read(Long id);

    Page<FullGroupDTO> readAll(PageRequest pageRequest);

    GroupDTO update(GroupDTO group);

    void delete(Long id);
}

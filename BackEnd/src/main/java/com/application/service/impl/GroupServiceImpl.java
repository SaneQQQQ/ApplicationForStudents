package com.application.service.impl;

import com.application.dao.GroupDAO;
import com.application.dto.FullGroupDTO;
import com.application.dto.GroupDTO;
import com.application.mapper.FullGroupMapper;
import com.application.mapper.GroupMapper;
import com.application.service.GroupService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GroupServiceImpl implements GroupService {

    private final GroupDAO groupDAO;
    private final GroupMapper groupMapper = GroupMapper.INSTANCE;
    private final FullGroupMapper fullGroupMapper = FullGroupMapper.INSTANCE;

    @Autowired
    public GroupServiceImpl(GroupDAO groupDAO) {
        this.groupDAO = groupDAO;
    }

    @Override
    @Transactional
    public GroupDTO create(GroupDTO group) {
        return groupMapper.toDTO(groupDAO.create(groupMapper.toEntity(group)));
    }


    @Override
    @Transactional(readOnly = true)
    public GroupDTO read(Long id) {
        return groupMapper.toDTO(groupDAO.read(id).orElseThrow(
                () -> new EntityNotFoundException("Group with id " + id + " not found")));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<FullGroupDTO> readAll(PageRequest pageRequest) {
        return groupDAO.readAll(pageRequest).map(fullGroupMapper::toDTO);
    }

    @Override
    @Transactional
    public GroupDTO update(GroupDTO group) {
        return groupMapper.toDTO(groupDAO.update(groupMapper.toEntity(group)));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        groupDAO.delete(id);
    }
}

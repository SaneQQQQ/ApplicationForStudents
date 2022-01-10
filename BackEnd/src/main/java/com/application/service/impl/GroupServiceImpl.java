package com.application.service.impl;

import com.application.dao.impl.GroupDAOImpl;
import com.application.dto.GroupDTO;
import com.application.mapper.GroupMapper;
import com.application.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class GroupServiceImpl implements GroupService {

    private final GroupDAOImpl groupDAO;

    @Autowired
    public GroupServiceImpl(GroupDAOImpl groupDAO) {
        this.groupDAO = groupDAO;
    }

    @Override
    @Transactional
    public GroupDTO create(GroupDTO group) {
        return GroupMapper.INSTANCE.toDTO(groupDAO.create(GroupMapper.INSTANCE.toEntity(group)));
    }

    @Override
    @Transactional(readOnly = true)
    public GroupDTO read(Long id) {
        return GroupMapper.INSTANCE.toDTO(groupDAO.read(id).orElseThrow(() -> new EntityNotFoundException("Group with id " + id + " not found")));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<GroupDTO> readAll(PageRequest pageRequest) {
        return groupDAO.readAll(pageRequest).map(GroupMapper.INSTANCE::toDTO);
    }

    @Override
    @Transactional
    public GroupDTO update(GroupDTO group) {
        return GroupMapper.INSTANCE.toDTO(groupDAO.update(GroupMapper.INSTANCE.toEntity(group)));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        groupDAO.delete(id);
    }
}

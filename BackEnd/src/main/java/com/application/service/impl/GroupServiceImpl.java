package com.application.service.impl;

import com.application.dao.impl.GroupDAOImpl;
import com.application.dto.GroupDTO;
import com.application.mapper.GroupMapper;
import com.application.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<GroupDTO> readAll() {
        return groupDAO.readAll().stream().map(GroupMapper.INSTANCE::toDTO).collect(Collectors.toList());
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

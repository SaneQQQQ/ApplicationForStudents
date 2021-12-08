package com.application.service.impl;

import com.application.dao.impl.GroupDAOImpl;
import com.application.model.Group;
import com.application.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class GroupServiceImpl implements GroupService {

    private final GroupDAOImpl groupDAO;

    @Autowired
    public GroupServiceImpl(GroupDAOImpl groupDAO) {
        this.groupDAO = groupDAO;
    }

    @Override
    @Transactional
    public Group create(Group group) {
        return groupDAO.create(group).orElseThrow(() -> new EntityNotFoundException("Group with id " + group.getId() + " was not created"));
    }

    @Override
    @Transactional(readOnly = true)
    public Group read(Long id) {
        return groupDAO.read(id).orElseThrow(() -> new EntityNotFoundException("Group with id " + id + " not found"));
    }

    @Override
    @Transactional
    public boolean update(Group group) {
        try {
            Group readGroup = read(group.getId());
        } catch (EntityNotFoundException e) {
            return false;
        }
        return groupDAO.update(group);
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        return groupDAO.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Group> readAll() {
        return groupDAO.readAll();
    }
}

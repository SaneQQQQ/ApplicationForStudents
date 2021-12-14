package com.application.service.impl;

import com.application.dao.impl.GroupDAOImpl;
import com.application.model.Group;
import com.application.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    private final GroupDAOImpl groupDAO;

    @Autowired
    public GroupServiceImpl(GroupDAOImpl groupDAO) {
        this.groupDAO = groupDAO;
    }

    @Override
    @Transactional
    public Long create(Group group) {
        return (Long) groupDAO.create(group);
    }

    @Override
    @Transactional(readOnly = true)
    public Group read(Long id) {
        return groupDAO.read(id).orElseThrow(() -> new EntityNotFoundException("Group with id " + id + " not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Group> readAll() {
        return groupDAO.readAll();
    }

    @Override
    @Transactional
    public Group update(Group group) {
        return groupDAO.update(group);
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        if (!groupDAO.delete(id)) {
            throw new EntityNotFoundException("Group with id " + id + " not found");
        }
        return true;
    }
}

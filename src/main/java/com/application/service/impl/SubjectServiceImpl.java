package com.application.service.impl;

import com.application.dao.impl.SubjectDAOImpl;
import com.application.model.Subject;
import com.application.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    private final SubjectDAOImpl subjectDAO;

    @Autowired
    public SubjectServiceImpl(SubjectDAOImpl subjectDAO) {
        this.subjectDAO = subjectDAO;
    }

    @Override
    @Transactional
    public SubjectDTO create(SubjectDTO subject) {
        return SubjectMapper.INSTANCE.subjectToSubjectDTO(subjectDAO.create(SubjectMapper.INSTANCE.subjectDTOToSubject(subject)));
    }

    @Override
    @Transactional(readOnly = true)
    public Subject read(Long id) {
        return subjectDAO.read(id).orElseThrow(() -> new EntityNotFoundException("Subject with id " + id + " not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Subject> readAll() {
        return subjectDAO.readAll();
    }

    @Override
    @Transactional
    public Subject update(Subject subject) {
        return subjectDAO.update(subject);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        subjectDAO.delete(id);
    }
}

package com.application.service.impl;

import com.application.dao.impl.SubjectDAOImpl;
import com.application.dto.SubjectDTO;
import com.application.mapper.SubjectMapper;
import com.application.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

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
    public SubjectDTO read(Long id) {
        return SubjectMapper.INSTANCE.subjectToSubjectDTO(subjectDAO.read(id).orElseThrow(() -> new EntityNotFoundException("Subject with id " + id + " not found")));
    }

    @Override
    @Transactional(readOnly = true)
    public List<SubjectDTO> readAll() {
        return subjectDAO.readAll().stream().map(SubjectMapper.INSTANCE::subjectToSubjectDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public SubjectDTO update(SubjectDTO subject) {
        return SubjectMapper.INSTANCE.subjectToSubjectDTO(subjectDAO.update(SubjectMapper.INSTANCE.subjectDTOToSubject(subject)));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        subjectDAO.delete(id);
    }
}

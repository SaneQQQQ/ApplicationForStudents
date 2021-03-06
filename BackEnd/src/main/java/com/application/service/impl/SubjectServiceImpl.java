package com.application.service.impl;

import com.application.dao.impl.SubjectDAOImpl;
import com.application.dto.SubjectDTO;
import com.application.mapper.SubjectMapper;
import com.application.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

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
        return SubjectMapper.INSTANCE.toDTO(subjectDAO.create(SubjectMapper.INSTANCE.toEntity(subject)));
    }

    @Override
    @Transactional(readOnly = true)
    public SubjectDTO read(Long id) {
        return SubjectMapper.INSTANCE.toDTO(subjectDAO.read(id).orElseThrow(() -> new EntityNotFoundException("Subject with id " + id + " not found")));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SubjectDTO> readAll(PageRequest pageRequest) {
        return subjectDAO.readAll(pageRequest).map(SubjectMapper.INSTANCE::toDTO);
    }

    @Override
    @Transactional
    public SubjectDTO update(SubjectDTO subject) {
        return SubjectMapper.INSTANCE.toDTO(subjectDAO.update(SubjectMapper.INSTANCE.toEntity(subject)));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        subjectDAO.delete(id);
    }
}

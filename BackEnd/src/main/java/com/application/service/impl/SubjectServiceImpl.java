package com.application.service.impl;

import com.application.dao.SubjectDAO;
import com.application.dto.FullSubjectDTO;
import com.application.dto.SubjectDTO;
import com.application.mapper.FullSubjectMapper;
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

    private final SubjectDAO subjectDAO;
    private final SubjectMapper subjectMapper = SubjectMapper.INSTANCE;
    private final FullSubjectMapper fullSubjectMapper = FullSubjectMapper.INSTANCE;

    @Autowired
    public SubjectServiceImpl(SubjectDAO subjectDAO) {
        this.subjectDAO = subjectDAO;
    }

    @Override
    @Transactional
    public SubjectDTO create(SubjectDTO subject) {
        return subjectMapper.toDTO(subjectDAO.create(subjectMapper.toEntity(subject)));
    }

    @Override
    @Transactional(readOnly = true)
    public SubjectDTO read(Long id) {
        return subjectMapper.toDTO(subjectDAO.read(id).orElseThrow(
                () -> new EntityNotFoundException("Subject with id " + id + " not found")));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<FullSubjectDTO> readAll(PageRequest pageRequest) {
        return subjectDAO.readAll(pageRequest).map(fullSubjectMapper::toDTO);
    }

    @Override
    @Transactional
    public SubjectDTO update(SubjectDTO subject) {
        return SubjectMapper.INSTANCE.toDTO(subjectDAO.update(subjectMapper.toEntity(subject)));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        subjectDAO.delete(id);
    }
}

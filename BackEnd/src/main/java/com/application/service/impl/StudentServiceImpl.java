package com.application.service.impl;

import com.application.dao.impl.StudentDAOImpl;
import com.application.dto.FullStudentDTO;
import com.application.dto.StudentDTO;
import com.application.mapper.FullStudentMapper;
import com.application.mapper.StudentMapper;
import com.application.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentDAOImpl studentDAO;

    @Autowired
    public StudentServiceImpl(StudentDAOImpl studentDAO) {
        this.studentDAO = studentDAO;
    }

    @Override
    @Transactional
    public FullStudentDTO create(FullStudentDTO student) {
        return FullStudentMapper.INSTANCE.toDTO(studentDAO.create(FullStudentMapper.INSTANCE.toEntity(student)));
    }

    @Override
    @Transactional(readOnly = true)
    public FullStudentDTO read(Long id) {
        return FullStudentMapper.INSTANCE.toDTO(studentDAO.read(id).orElseThrow(() -> new EntityNotFoundException("Student with id " + id + " not found")));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<FullStudentDTO> readAll(PageRequest pageRequest) {
        return studentDAO.readAll(pageRequest).map(FullStudentMapper.INSTANCE::toDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<StudentDTO> readAllByGroupId(Long id, PageRequest pageRequest) {
        return studentDAO.readAllByGroupId(id, pageRequest).map(StudentMapper.INSTANCE::toDTO);
    }

    @Override
    @Transactional
    public FullStudentDTO update(FullStudentDTO student) {
        return FullStudentMapper.INSTANCE.toDTO(studentDAO.update(FullStudentMapper.INSTANCE.toEntity(student)));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        studentDAO.delete(id);
    }
}

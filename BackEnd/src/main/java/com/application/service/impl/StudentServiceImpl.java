package com.application.service.impl;

import com.application.dao.StudentDAO;
import com.application.dto.FullStudentDTO;
import com.application.dto.StudentDTO;
import com.application.mapper.FullStudentMapper;
import com.application.mapper.StudentMapper;
import com.application.service.StudentService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentDAO studentDAO;
    private final FullStudentMapper fullStudentMapper = FullStudentMapper.INSTANCE;
    private final StudentMapper studentMapper = StudentMapper.INSTANCE;


    @Autowired
    public StudentServiceImpl(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    @Override
    @Transactional
    public FullStudentDTO create(FullStudentDTO student) {
        return fullStudentMapper.toDTO(studentDAO.create(fullStudentMapper.toEntity(student)));
    }

    @Override
    @Transactional(readOnly = true)
    public FullStudentDTO read(Long id) {
        return fullStudentMapper.toDTO(studentDAO.read(id).orElseThrow(
                () -> new EntityNotFoundException("Student with id " + id + " not found")));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<FullStudentDTO> readAll(PageRequest pageRequest) {
        return studentDAO.readAll(pageRequest).map(fullStudentMapper::toDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<StudentDTO> readAllByGroupId(Long id, PageRequest pageRequest) {
        return studentDAO.readAllByGroupId(id, pageRequest).map(studentMapper::toDTO);
    }

    @Override
    @Transactional
    public FullStudentDTO update(FullStudentDTO student) {
        return fullStudentMapper.toDTO(studentDAO.update(fullStudentMapper.toEntity(student)));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        studentDAO.delete(id);
    }
}

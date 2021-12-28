package com.application.service.impl;

import com.application.dao.impl.StudentDAOImpl;
import com.application.dto.FullStudentDTO;
import com.application.dto.StudentDTO;
import com.application.mapper.FullStudentMapper;
import com.application.mapper.StudentMapper;
import com.application.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<FullStudentDTO> readAll() {
        return studentDAO.readAll().stream().map(FullStudentMapper.INSTANCE::toDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudentDTO> readAllByGroupId(Long id) {
        return studentDAO.readAllByGroupId(id).stream().map(StudentMapper.INSTANCE::toDTO).collect(Collectors.toList());
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
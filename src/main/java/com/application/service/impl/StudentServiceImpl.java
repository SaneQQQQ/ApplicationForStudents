package com.application.service.impl;

import com.application.dao.impl.StudentDAOImpl;
import com.application.model.Student;
import com.application.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

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
        return FullStudentMapper.INSTANCE.studentToFullStudentDTO(studentDAO.create(FullStudentMapper.INSTANCE.fullStudentDTOToStudent(student)));
    }

    @Override
    @Transactional(readOnly = true)
    public FullStudentDTO read(Long id) {
        return FullStudentMapper.INSTANCE.studentToFullStudentDTO(studentDAO.read(id).orElseThrow(() -> new EntityNotFoundException("Student with id " + id + " not found")));
    }

    @Override
    @Transactional(readOnly = true)
    public List<FullStudentDTO> readAll() {
        return studentDAO.readAll().stream().map(FullStudentMapper.INSTANCE::studentToFullStudentDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudentDTO> readAllByGroupId(Long id) {
        return studentDAO.readAllByGroupId(id).stream().map(StudentMapper.INSTANCE::studentToStudentDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public FullStudentDTO update(FullStudentDTO student) {
        return FullStudentMapper.INSTANCE.studentToFullStudentDTO(studentDAO.update(FullStudentMapper.INSTANCE.fullStudentDTOToStudent(student)));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        studentDAO.delete(id);
    }
}

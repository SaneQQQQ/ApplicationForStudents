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
    public Student create(Student student) {
        return studentDAO.create(student).orElseThrow(() -> new EntityNotFoundException("Student with id " + student.getId() + " was not created"));
    }

    @Override
    @Transactional(readOnly = true)
    public Student read(Long id) {
        return studentDAO.read(id).orElseThrow(() -> new EntityNotFoundException("Student with id " + id + " not found"));
    }

    @Override
    @Transactional
    public Student update(Student student) {
        return studentDAO.update(student);
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        return studentDAO.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Student> readAll() {
        return studentDAO.readAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Student> readAllByGroupId(Long id) {
        return studentDAO.readAllByGroupId(id);
    }
}

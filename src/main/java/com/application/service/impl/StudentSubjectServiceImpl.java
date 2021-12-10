package com.application.service.impl;

import com.application.dao.impl.StudentSubjectDAOImpl;
import com.application.model.StudentSubject;
import com.application.service.StudentSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class StudentSubjectServiceImpl implements StudentSubjectService {

    private final StudentSubjectDAOImpl studentSubjectDAO;

    @Autowired
    public StudentSubjectServiceImpl(StudentSubjectDAOImpl studentSubjectDAO) {
        this.studentSubjectDAO = studentSubjectDAO;
    }

    @Override
    @Transactional
    public StudentSubject create(StudentSubject studentSubject) {
        return studentSubjectDAO.create(studentSubject).orElseThrow(() -> new EntityNotFoundException("Mark with student_id " + studentSubject.getStudent().getId() + " and subject_id" + studentSubject.getSubject().getId() + " was not created"));
    }

    @Override
    @Transactional(readOnly = true)
    public StudentSubject read(Long studentId, Long subjectId) {
        return studentSubjectDAO.read(studentId, subjectId).orElseThrow(() -> new EntityNotFoundException("Mark with student_id " + studentId + " and subject_id" + subjectId + " not found"));
    }

    @Override
    @Transactional
    public boolean update(StudentSubject studentSubject) {
        try {
            StudentSubject readStudentSubject = read(studentSubject.getStudent().getId(), studentSubject.getSubject().getId());
        } catch (EntityNotFoundException e) {
            return false;
        }
        return studentSubjectDAO.update(studentSubject);
    }

    @Override
    @Transactional
    public boolean delete(Long studentId, Long subjectId) {
        return studentSubjectDAO.delete(studentId, subjectId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudentSubject> readAll() {
        return studentSubjectDAO.readAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudentSubject> readAllByStudentId(Long id) {
        return studentSubjectDAO.readAllByStudentId(id);
    }
}

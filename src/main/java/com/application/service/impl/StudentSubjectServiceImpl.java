package com.application.service.impl;

import com.application.dao.impl.StudentSubjectDAOImpl;
import com.application.model.StudentSubject;
import com.application.model.StudentSubjectId;
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
    public StudentSubjectId create(StudentSubject studentSubject) {
        return (StudentSubjectId) studentSubjectDAO.create(studentSubject);
    }

    @Override
    @Transactional(readOnly = true)
    public FullStudentSubjectDTO read(Long studentId, Long subjectId) {
        Student student = new Student();
        student.setId(studentId);
        Subject subject = new Subject();
        subject.setId(subjectId);
        StudentSubjectId studentSubjectId = new StudentSubjectId();
        studentSubjectId.setSubject(subject);
        studentSubjectId.setStudent(student);
        return FullStudentSubjectMapper.INSTANCE.studentSubjectToStudentSubjectDTO(studentSubjectDAO.read(studentSubjectId).orElseThrow(() -> new EntityNotFoundException("Mark with student_id " + studentId + " and subject_id " + subjectId + " not found")));
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudentSubjectDTO> readAllByStudentId(Long id) {
        return studentSubjectDAO.readAllByStudentId(id).stream().map(StudentSubjectMapper.INSTANCE::studentSubjectToStudentSubjectDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public FullStudentSubjectDTO update(FullStudentSubjectDTO studentSubject) {
        return FullStudentSubjectMapper.INSTANCE.studentSubjectToStudentSubjectDTO(studentSubjectDAO.update(FullStudentSubjectMapper.INSTANCE.studentSubjectDTOToStudentSubject(studentSubject)));

    }

    @Override
    @Transactional
    public boolean delete(Long studentId, Long subjectId) {
        if (!studentSubjectDAO.delete(studentId, subjectId)) {
            throw new EntityNotFoundException("Mark with student_id " + studentId + " and subject_id " + subjectId + " not found");
        }
        return true;
    }
}

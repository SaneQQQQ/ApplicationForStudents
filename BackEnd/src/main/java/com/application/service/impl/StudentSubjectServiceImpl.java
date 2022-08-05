package com.application.service.impl;

import com.application.dao.impl.StudentSubjectDAOImpl;
import com.application.dto.FullStudentSubjectDTO;
import com.application.dto.StudentSubjectDTO;
import com.application.mapper.FullStudentSubjectMapper;
import com.application.mapper.StudentSubjectMapper;
import com.application.model.Student;
import com.application.model.StudentSubjectId;
import com.application.model.Subject;
import com.application.service.StudentSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class StudentSubjectServiceImpl implements StudentSubjectService {

    private final StudentSubjectDAOImpl studentSubjectDAO;
    private final FullStudentSubjectMapper fullStudentSubjectMapper = FullStudentSubjectMapper.INSTANCE;
    private final StudentSubjectMapper studentSubjectMapper = StudentSubjectMapper.INSTANCE;

    @Autowired
    public StudentSubjectServiceImpl(StudentSubjectDAOImpl studentSubjectDAO) {
        this.studentSubjectDAO = studentSubjectDAO;
    }

    @Override
    @Transactional
    public FullStudentSubjectDTO create(FullStudentSubjectDTO studentSubject) {
        return fullStudentSubjectMapper.toDTO(studentSubjectDAO.create(fullStudentSubjectMapper.toEntity(studentSubject)));
    }

    @Override
    @Transactional(readOnly = true)
    public FullStudentSubjectDTO read(Long studentId, Long subjectId) {
        return fullStudentSubjectMapper.toDTO(studentSubjectDAO.read(createStudentSubjectId(studentId, subjectId)).orElseThrow(
                () -> new EntityNotFoundException("Mark with student_id " + studentId + " and subject_id " + subjectId + " not found")));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<StudentSubjectDTO> readAllByStudentId(Long id, PageRequest pageRequest) {
        return studentSubjectDAO.readAllByStudentId(id, pageRequest).map(studentSubjectMapper::toDTO);
    }

    @Override
    @Transactional
    public FullStudentSubjectDTO update(FullStudentSubjectDTO studentSubject) {
        return fullStudentSubjectMapper.toDTO(studentSubjectDAO.update(fullStudentSubjectMapper.toEntity(studentSubject)));

    }

    @Override
    @Transactional
    public void delete(Long studentId, Long subjectId) {
        studentSubjectDAO.delete(createStudentSubjectId(studentId, subjectId));
    }

    private StudentSubjectId createStudentSubjectId(Long studentId, Long subjectId) {
        Student student = new Student();
        student.setId(studentId);
        Subject subject = new Subject();
        subject.setId(subjectId);
        StudentSubjectId studentSubjectId = new StudentSubjectId();
        studentSubjectId.setSubject(subject);
        studentSubjectId.setStudent(student);
        return studentSubjectId;
    }
}

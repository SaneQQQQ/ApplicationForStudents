package com.application.dao.impl;

import com.application.dao.StudentSubjectDAO;
import com.application.model.StudentSubject;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Repository
public class StudentSubjectDAOImpl extends BaseDAOImpl<StudentSubject> implements StudentSubjectDAO {

    // TODO no need for this method, StudentSubjectId should be used as a parameter instead, this way the method from the BaseOperationsDAOImpl will be invoked
    public Optional<StudentSubject> read(Long studentId, Long subjectId) {
        StudentSubject studentSubject = sessionFactory.getCurrentSession().createNativeQuery("SELECT * FROM students_subjects WHERE student_id=? AND subject_id=?", StudentSubject.class)
                .setParameter(1, studentId)
                .setParameter(2, subjectId)
                .getSingleResult();
        if (studentSubject == null) {
            return Optional.empty();
        }
        studentSubject.getStudent().getGroup().getTitle();
        studentSubject.getSubject().getTitle();
        return Optional.of(studentSubject);
    }

    // TODO no need for this method, StudentSubjectId should be used as a parameter instead, this way the method from the BaseOperationsDAOImpl will be invoked
    public boolean delete(Long studentId, Long subjectId) {
        sessionFactory.getCurrentSession().createNativeQuery("DELETE FROM students_subjects WHERE student_id=? AND subject_id=?")
                .setParameter(1, studentId)
                .setParameter(2, subjectId)
                .executeUpdate();
        return true;
    }

    public List<StudentSubject> readAllByStudentId(Long id) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<StudentSubject> criteriaQuery = criteriaBuilder.createQuery(StudentSubject.class);
        Root<StudentSubject> root = criteriaQuery.from(StudentSubject.class);
        Predicate studentId = (criteriaBuilder.equal(root.get("student"), id));
        criteriaQuery.where(studentId);
        Query<StudentSubject> query = session.createQuery(criteriaQuery);
        List<StudentSubject> studentSubjects = query.getResultList();
        // TODO move to the service layer
        for (StudentSubject studentSubject : studentSubjects) {
            studentSubject.getStudent().getGroup().getTitle();
            studentSubject.getSubject().getTitle();
        }
        return studentSubjects;
    }
}

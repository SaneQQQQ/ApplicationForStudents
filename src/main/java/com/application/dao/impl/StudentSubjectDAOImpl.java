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

@Repository
public class StudentSubjectDAOImpl extends BaseDAOImpl<StudentSubject> implements StudentSubjectDAO {

    public List<StudentSubject> readAllByStudentId(Long id) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<StudentSubject> criteriaQuery = criteriaBuilder.createQuery(StudentSubject.class);
        Root<StudentSubject> root = criteriaQuery.from(StudentSubject.class);
        Predicate studentId = (criteriaBuilder.equal(root.get("student"), id));
        criteriaQuery.where(studentId);
        Query<StudentSubject> query = session.createQuery(criteriaQuery);
        return query.getResultList();
    }

}

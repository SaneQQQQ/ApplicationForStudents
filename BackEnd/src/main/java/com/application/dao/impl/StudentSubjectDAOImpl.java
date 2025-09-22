package com.application.dao.impl;

import com.application.dao.StudentSubjectDAO;
import com.application.model.StudentSubject;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentSubjectDAOImpl extends BaseDAOImpl<StudentSubject> implements StudentSubjectDAO {

    @Override
    public Page<StudentSubject> readAllByStudentId(Long id, Pageable pageable) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<StudentSubject> criteriaQuery = criteriaBuilder.createQuery(StudentSubject.class);
        Root<StudentSubject> root = criteriaQuery.from(StudentSubject.class);
        setSortOrder(pageable, criteriaBuilder, criteriaQuery, root);
        Predicate studentId = criteriaBuilder.equal(root.get("student").get("id"), id);
        criteriaQuery.where(studentId);
        Query<StudentSubject> query = session.createQuery(criteriaQuery);
        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());
        List<StudentSubject> studentSubjects = query.getResultList();
        Query<Long> queryCount = session.createNativeQuery("SELECT COUNT(mark) FROM students_subjects WHERE student_id = :id", Long.class);
        queryCount.setParameter("id", id);
        long count = queryCount.getSingleResult();
        return new PageImpl<>(studentSubjects, pageable, count);
    }
}

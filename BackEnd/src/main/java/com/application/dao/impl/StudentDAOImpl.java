package com.application.dao.impl;

import com.application.dao.StudentDAO;
import com.application.model.Student;
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
public class StudentDAOImpl extends BaseDAOImpl<Student> implements StudentDAO {
    @Override
    public Page<Student> readAllByGroupId(Long id, Pageable pageable) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Student> criteriaQuery = criteriaBuilder.createQuery(Student.class);
        Root<Student> root = criteriaQuery.from(Student.class);
        setSortOrder(pageable, criteriaBuilder, criteriaQuery, root);
        Predicate groupId = criteriaBuilder.equal(root.get("group").get("id"), id);
        criteriaQuery.where(groupId);
        Query<Student> query = session.createQuery(criteriaQuery);
        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());
        List<Student> students = query.getResultList();
        Query<Long> queryCount = session.createQuery("select count(s.id) from students s where s.group.id = :id", Long.class);
        queryCount.setParameter("id", id);
        long count = queryCount.getSingleResult();
        return new PageImpl<>(students, pageable, count);
    }

}

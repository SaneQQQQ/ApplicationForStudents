package com.application.dao.impl;

import com.application.dao.StudentDAO;
import com.application.model.Student;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class StudentDAOImpl extends BaseDAOImpl<Student> implements StudentDAO {

    public List<Student> readAllByGroupId(Long id) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Student> criteriaQuery = criteriaBuilder.createQuery(Student.class);
        Root<Student> root = criteriaQuery.from(Student.class);
        Predicate groupId = criteriaBuilder.equal(root.get("group"), id);
        criteriaQuery.where(groupId);
        Query<Student> query = session.createQuery(criteriaQuery);
        return query.getResultList();
    }

}

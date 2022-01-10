package com.application.dao.impl;

import com.application.dao.StudentDAO;
import com.application.model.Student;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class StudentDAOImpl extends BaseDAOImpl<Student> implements StudentDAO {

    @Override
    public Page<Student> readAll(Pageable pageable) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Student> criteriaQuery = criteriaBuilder.createQuery(Student.class);
        Root<Student> root = criteriaQuery.from(Student.class);
        if (pageable.getSort().isSorted()) {
            String property = pageable.getSort().iterator().next().getProperty();
            if (pageable.getSort().getOrderFor(property).getDirection().isDescending()) {
                criteriaQuery.orderBy(criteriaBuilder.desc(root.get(property)));
            } else {
                criteriaQuery.orderBy(criteriaBuilder.asc(root.get(property)));
            }
        }
        criteriaQuery.select(root);
        Query<Student> query = session.createQuery(criteriaQuery);
        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());
        List<Student> students = query.getResultList();
        Query queryCount = session.createQuery("select count(s.id) from students s");
        long count = (long) queryCount.getSingleResult();
        return new PageImpl<>(students, pageable, count);
    }

    @Override
    public Page<Student> readAllByGroupId(Long id, Pageable pageable) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Student> criteriaQuery = criteriaBuilder.createQuery(Student.class);
        Root<Student> root = criteriaQuery.from(Student.class);
        if (pageable.getSort().isSorted()) {
            String property = pageable.getSort().iterator().next().getProperty();
            if (pageable.getSort().getOrderFor(property).getDirection().isDescending()) {
                criteriaQuery.orderBy(criteriaBuilder.desc(root.get(property)));
            } else {
                criteriaQuery.orderBy(criteriaBuilder.asc(root.get(property)));
            }
        }
        Predicate groupId = criteriaBuilder.equal(root.get("group"), id);
        criteriaQuery.where(groupId);
        Query<Student> query = session.createQuery(criteriaQuery);
        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());
        List<Student> students = query.getResultList();
        Query queryCount = session.createQuery("select count(s.id) from students s where s.group.id = :id");
        queryCount.setParameter("id", id);
        long count = (long) queryCount.getSingleResult();
        return new PageImpl<>(students, pageable, count);
    }

}

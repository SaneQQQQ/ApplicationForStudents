package com.application.dao.impl;

import com.application.dao.SubjectDAO;
import com.application.model.Subject;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class SubjectDAOImpl extends BaseDAOImpl<Subject> implements SubjectDAO {

    public Page<Subject> readAllSortedByTitle(Pageable pageable) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Subject> criteriaQuery = criteriaBuilder.createQuery(Subject.class);
        Root<Subject> root = criteriaQuery.from(Subject.class);
        if (pageable.getSort().getOrderFor("title").getDirection().isDescending()) {
            criteriaQuery.orderBy(criteriaBuilder.desc(root.get("title")));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.asc(root.get("title")));
        }
        criteriaQuery.select(root);
        Query<Subject> query = session.createQuery(criteriaQuery);
        int pageNumber = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        query.setFirstResult((pageNumber) * pageSize);
        query.setMaxResults(pageSize);
        List<Subject> subjects = query.getResultList();
        Query queryCount = sessionFactory.getCurrentSession().createQuery("select count(s.id) from subjects s");
        long count = (long) queryCount.getSingleResult();
        return new PageImpl<>(subjects, pageable, count);
    }
}

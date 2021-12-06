package com.application.dao.impl;

import com.application.dao.BaseOperationsDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

public abstract class BaseOperationsDAOImpl<T> implements BaseOperationsDAO<T> {

    private final Class<T> tClass;
    protected final SessionFactory sessionFactory;

    public BaseOperationsDAOImpl(SessionFactory sessionFactory, Class<T> tClass) {
        this.sessionFactory = sessionFactory;
        this.tClass = tClass;
    }

    public void create(T t) {
        sessionFactory.getCurrentSession().save(t);
    }

    public Optional<T> read(Long id) {
        return Optional.of(sessionFactory.getCurrentSession().get(tClass, id));
    }

    public void update(T t) {
        sessionFactory.getCurrentSession().saveOrUpdate(t);
    }

    public void delete(Long id) {
        T t = sessionFactory.getCurrentSession().get(tClass, id);
        sessionFactory.getCurrentSession().delete(t);
    }

    public List<T> getAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(tClass);
        Root<T> root = criteriaQuery.from(tClass);
        criteriaQuery.select(root);
        Query<T> query = session.createQuery(criteriaQuery);
        return query.getResultList();
    }
}

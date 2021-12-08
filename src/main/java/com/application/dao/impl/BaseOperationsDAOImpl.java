package com.application.dao.impl;

import com.application.dao.BaseOperationsDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class BaseOperationsDAOImpl<T> implements BaseOperationsDAO<T> {

    private final Class<T> tClass;
    protected final SessionFactory sessionFactory;

    public BaseOperationsDAOImpl(SessionFactory sessionFactory, Class<T> tClass) {
        this.sessionFactory = sessionFactory;
        this.tClass = tClass;
    }

    public Optional<T> create(T t) {
        Serializable id = sessionFactory.getCurrentSession().save(t);
        return read(id);
    }

    public Optional<T> read(Serializable id) {
        T t = sessionFactory.getCurrentSession().get(tClass, id);
        if (t == null)
            return Optional.empty();
        return Optional.of(t);
    }

    public boolean update(T t) {
        sessionFactory.getCurrentSession().merge(t);
        return true;
    }

    public boolean delete(Serializable id) {
        T entity = sessionFactory.getCurrentSession().get(tClass, id);
        if (entity == null)
            return false;
        sessionFactory.getCurrentSession().delete(entity);
        return true;
    }

    public List<T> readAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(tClass);
        Root<T> root = criteriaQuery.from(tClass);
        criteriaQuery.select(root);
        Query<T> query = session.createQuery(criteriaQuery);
        return query.getResultList();
    }
}

package com.application.dao.impl;

import com.application.dao.BaseOperationsDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

public abstract class BaseOperationsDAOImpl<T> implements BaseOperationsDAO<T> {

    @Autowired
    protected SessionFactory sessionFactory;
    // TODO final class variables should be declared above non-final
    private final Class<T> tClass;

    @SuppressWarnings("unchecked")
    // TODO better to use protected
    public BaseOperationsDAOImpl() {
        this.tClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public Optional<T> create(T t) {
        Serializable id = sessionFactory.getCurrentSession().save(t);
        // TODO redundant call
        return read(id);
    }

    public Optional<T> read(Serializable id) {
        T t = sessionFactory.getCurrentSession().get(tClass, id);
        // TODO leave curved brackets no matter what
        if (t == null)
            return Optional.empty();
        return Optional.of(t);
    }

    @SuppressWarnings("unchecked")
    public T update(T t) {
        return (T) sessionFactory.getCurrentSession().merge(t);
    }

    public boolean delete(Serializable id) {
        T entity = sessionFactory.getCurrentSession().get(tClass, id);
        // TODO leave curved brackets no matter what
        if (entity == null)
            // TODO why false?
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

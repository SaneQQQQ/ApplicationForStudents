package com.application.dao.impl;

import com.application.dao.BaseDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

public abstract class BaseDAOImpl<T> implements BaseDAO<T> {

    private Class<T> tClass;

    @Autowired
    protected SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    protected BaseDAOImpl() {
        this.tClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public T create(T t) {
        sessionFactory.getCurrentSession().save(t);
        return t;
    }

    public Optional<T> read(Serializable id) {
        T t = sessionFactory.getCurrentSession().get(tClass, id);
        if (t == null) {
            return Optional.empty();
        }
        return Optional.of(t);
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

    public T update(T t) {
        sessionFactory.getCurrentSession().update(t);
        return t;
    }

    public boolean delete(Serializable id) {
        T entity = sessionFactory.getCurrentSession().get(tClass, id);
        if (entity == null)
            return true;
        sessionFactory.getCurrentSession().delete(entity);
        return true;
    }

    protected Path<Object> createPath(String property, Root<T> root) {
        String[] splitProp = property.split("\\.");
        Path<Object> path = root.get(splitProp[0]);
        for (int i = 1; i < splitProp.length; i++) {
            path = path.get(splitProp[i]);
        }
        return path;
    }
}

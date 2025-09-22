package com.application.dao.impl;

import com.application.dao.BaseDAO;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

public abstract class BaseDAOImpl<T> implements BaseDAO<T> {

    private final Class<T> tClass;

    @Autowired
    protected SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    protected BaseDAOImpl() {
        this.tClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public T create(T t) {
        sessionFactory.getCurrentSession().persist(t);
        return t;
    }

    public Optional<T> read(Serializable id) {
        T t = sessionFactory.getCurrentSession().find(tClass, id);
        if (t == null) {
            return Optional.empty();
        }
        return Optional.of(t);
    }

    public Page<T> readAll(Pageable pageable) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(tClass);
        Root<T> root = criteriaQuery.from(tClass);
        setSortOrder(pageable, criteriaBuilder, criteriaQuery, root);
        criteriaQuery.select(root);
        Query<T> query = session.createQuery(criteriaQuery);
        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());
        List<T> groups = query.getResultList();
        long count = getCountOfRecords(session, criteriaBuilder);
        return new PageImpl<>(groups, pageable, count);
    }

    public T update(T t) {
        sessionFactory.getCurrentSession().merge(t);
        return t;
    }

    public boolean delete(Serializable id) {
        T entity = sessionFactory.getCurrentSession().find(tClass, id);
        if (entity == null)
            return true;
        sessionFactory.getCurrentSession().remove(entity);
        return true;
    }

    void setSortOrder(Pageable pageable, CriteriaBuilder criteriaBuilder, CriteriaQuery<T> criteriaQuery, Root<T> root) {
        Sort sort = pageable.getSort();
        if (sort.isSorted()) {
            String property = sort.iterator().next().getProperty();
            switch (sort.getOrderFor(property).getDirection()) {
                case ASC:
                    criteriaQuery.orderBy(criteriaBuilder.asc(createPath(property, root)));
                    break;
                case DESC:
                    criteriaQuery.orderBy(criteriaBuilder.desc(createPath(property, root)));
                    break;
            }
        }
    }

    private Path<T> createPath(String property, Root<T> root) {
        String[] splitProperty = property.split("\\.");
        Path<T> path = root.get(splitProperty[0]);
        for (int i = 1; i < splitProperty.length; i++) {
            path = path.get(splitProperty[i]);
        }
        return path;
    }

    private Long getCountOfRecords(Session session, CriteriaBuilder criteriaBuilder) {
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<T> root = criteriaQuery.from(tClass);
        criteriaQuery.select(criteriaBuilder.count(root));
        Query<Long> query = session.createQuery(criteriaQuery);
        return query.getSingleResult();
    }
}

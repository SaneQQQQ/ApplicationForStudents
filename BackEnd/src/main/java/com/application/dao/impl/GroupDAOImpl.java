package com.application.dao.impl;

import com.application.dao.GroupDAO;
import com.application.model.Group;
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
public class GroupDAOImpl extends BaseDAOImpl<Group> implements GroupDAO {

    @Override
    public Page<Group> readAll(Pageable pageable) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Group> criteriaQuery = criteriaBuilder.createQuery(Group.class);
        Root<Group> root = criteriaQuery.from(Group.class);
        if (pageable.getSort().isSorted()) {
            String property = pageable.getSort().iterator().next().getProperty();
            if (pageable.getSort().getOrderFor(property).getDirection().isDescending()) {
                criteriaQuery.orderBy(criteriaBuilder.desc(root.get(property)));
            } else {
                criteriaQuery.orderBy(criteriaBuilder.asc(root.get(property)));
            }
        }
        criteriaQuery.select(root);
        Query<Group> query = session.createQuery(criteriaQuery);
        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());
        List<Group> groups = query.getResultList();
        Query queryCount = session.createQuery("select count(g.id) from groups g");
        long count = (long) queryCount.getSingleResult();
        return new PageImpl<>(groups, pageable, count);
    }
}

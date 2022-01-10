package com.application.dao.impl;

import com.application.dao.StudentSubjectDAO;
import com.application.model.StudentSubject;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.type.LongType;
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
public class StudentSubjectDAOImpl extends BaseDAOImpl<StudentSubject> implements StudentSubjectDAO {

    @Override
    public Page<StudentSubject> readAllByStudentId(Long id, Pageable pageable) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<StudentSubject> criteriaQuery = criteriaBuilder.createQuery(StudentSubject.class);
        Root<StudentSubject> root = criteriaQuery.from(StudentSubject.class);
        if (pageable.getSort().isSorted()) {
            String property = pageable.getSort().iterator().next().getProperty();
            if (pageable.getSort().getOrderFor(property).getDirection().isDescending()) {
                criteriaQuery.orderBy(criteriaBuilder.desc(root.get(property)));
            } else {
                criteriaQuery.orderBy(criteriaBuilder.asc(root.get(property)));
            }
        }
        Predicate studentId = (criteriaBuilder.equal(root.get("student"), id));
        criteriaQuery.where(studentId);
        Query<StudentSubject> query = session.createQuery(criteriaQuery);
        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());
        List<StudentSubject> studentSubjects = query.getResultList();
        Query queryCount = session.createNativeQuery("select count(mark) from students_subjects where student_id = :id")
                .addScalar("count", LongType.INSTANCE);
        queryCount.setParameter("id", id);
        long count = (long) queryCount.getSingleResult();
        return new PageImpl<>(studentSubjects, pageable, count);
    }
}

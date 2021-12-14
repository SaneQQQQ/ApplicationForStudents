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
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Repository
public class StudentDAOImpl extends BaseDAOImpl<Student> implements StudentDAO {

    // TODO move to the service layer
    @Override
    public Optional<Student> read(Serializable id) {
        Optional<Student> student = super.read(id);
        if (student.isEmpty()) {
            return Optional.empty();
        }
        student.get().getGroup().getTitle();
        return student;
    }
    // TODO move to the service layer
    @Override
    public List<Student> readAll() {
        List<Student> students = super.readAll();
        for (Student student : students) {
            student.getGroup().getTitle();
        }
        return students;
    }
    // TODO move to the service layer
    @Override
    public Student update(Student student) {
        Student updatedStudent = super.update(student);
        updatedStudent.getGroup().getTitle();
        return updatedStudent;
    }

    public List<Student> readAllByGroupId(Long id) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Student> criteriaQuery = criteriaBuilder.createQuery(Student.class);
        Root<Student> root = criteriaQuery.from(Student.class);
        Predicate groupId = criteriaBuilder.equal(root.get("group"), id);
        criteriaQuery.where(groupId);
        Query<Student> query = session.createQuery(criteriaQuery);
        List<Student> students = query.getResultList();
        // TODO move to the service layer
        for (Student student : students) {
            student.getGroup().getTitle();
        }
        return students;
    }
}

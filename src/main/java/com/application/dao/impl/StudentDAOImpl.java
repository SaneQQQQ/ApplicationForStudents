package com.application.dao.impl;

import com.application.dao.StudentDAO;
import com.application.model.Student;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDAOImpl extends BaseOperationsDAOImpl<Student> implements StudentDAO {

    @Autowired
    public StudentDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory, Student.class);
    }
}

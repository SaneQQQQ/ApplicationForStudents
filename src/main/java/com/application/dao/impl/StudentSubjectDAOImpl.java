package com.application.dao.impl;

import com.application.dao.StudentSubjectDAO;
import com.application.model.StudentSubject;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StudentSubjectDAOImpl extends BaseOperationsDAOImpl<StudentSubject> implements StudentSubjectDAO {

    @Autowired
    public StudentSubjectDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory, StudentSubject.class);
    }
}

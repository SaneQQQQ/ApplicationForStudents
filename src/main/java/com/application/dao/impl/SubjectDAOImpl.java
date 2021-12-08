package com.application.dao.impl;

import com.application.dao.SubjectDAO;
import com.application.model.Subject;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SubjectDAOImpl extends BaseOperationsDAOImpl<Subject> implements SubjectDAO {

    @Autowired
    public SubjectDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory, Subject.class);;
    }
}

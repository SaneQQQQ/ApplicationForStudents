package com.application.dao;

import com.application.model.Subject;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SubjectDAO extends BaseOperationsDAO<Subject> {

    @Autowired
    public SubjectDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
        super.setClass(Subject.class);
    }

}

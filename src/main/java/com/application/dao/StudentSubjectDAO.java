package com.application.dao;

import com.application.model.StudentSubject;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StudentSubjectDAO extends BaseOperationsDAO<StudentSubject> {

    @Autowired
    public StudentSubjectDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
        super.setClass(StudentSubject.class);
    }

}

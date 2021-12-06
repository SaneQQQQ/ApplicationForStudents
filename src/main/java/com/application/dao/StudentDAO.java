package com.application.dao;

import com.application.model.Student;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDAO extends BaseOperationsDAO<Student> {

    @Autowired
    public StudentDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
        super.setClass(Student.class);
    }

}

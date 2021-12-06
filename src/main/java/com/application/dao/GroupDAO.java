package com.application.dao;

import com.application.model.Group;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GroupDAO extends BaseOperationsDAO<Group> {

    @Autowired
    public GroupDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
        super.setClass(Group.class);
    }

}

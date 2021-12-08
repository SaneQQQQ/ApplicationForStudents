package com.application.dao.impl;

import com.application.dao.GroupDAO;
import com.application.model.Group;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GroupDAOImpl extends BaseOperationsDAOImpl<Group> implements GroupDAO {

    @Autowired
    public GroupDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory, Group.class);
    }
}

package com.example.repositories.impl;

import com.example.domain.User;
import com.example.repositories.UserCustomRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by dani on 2017-02-10.
 */
public class UserRepositoryImpl implements UserCustomRepository{

    @Autowired
    private SessionFactory factory;

    @Override
    public List<User> getUsers(Long start, Long end) {
        Session session = factory.getCurrentSession();
        Query q = session.createQuery("SELECT u from com.example.domain.User u");
        q.setFirstResult(start.intValue());
        q.setMaxResults(end.intValue());
        List<User> userList = q.list();
        return userList;
    }
}

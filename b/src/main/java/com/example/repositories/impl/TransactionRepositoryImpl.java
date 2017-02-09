package com.example.repositories.impl;

import com.example.domain.Transaction;
import com.example.repositories.TransactionCustomRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by dani on 2017-02-09.
 */
public class TransactionRepositoryImpl implements TransactionCustomRepository {

    @Autowired
    private SessionFactory factory;

    @Override
    public List<Transaction> getTransactions(String username,Long start, Long end){
        Session session = factory.getCurrentSession();

        Query q = session.createQuery("SELECT t FROM com.example.domain.Transaction t, com.example.domain.User u JOIN t.user WHERE t.user = u.id AND u.username=:username");
        q.setParameter("username", username);
        q.setFirstResult(start.intValue());
        q.setMaxResults(end.intValue());
        List<Transaction> transactionList = q.list();

        return transactionList;
    }

}
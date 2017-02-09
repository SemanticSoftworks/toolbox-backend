package com.example.service.impl;

import com.example.domain.Transaction;
import com.example.repositories.TransactionRepository;
import com.example.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dani on 2017-02-09.
 */
@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public Transaction findByTransactionId(Long id) {
        return transactionRepository.findByTransactionId(id);
    }

    @Override
    public Transaction addTransaction(Transaction newTransaction) {
        return transactionRepository.save(newTransaction);
    }
}

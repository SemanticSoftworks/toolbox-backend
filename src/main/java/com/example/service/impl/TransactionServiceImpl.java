package com.example.service.impl;

import com.example.domain.Transaction;
import com.example.repositories.TransactionCustomRepository;
import com.example.repositories.TransactionRepository;
import com.example.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by dani on 2017-02-09.
 */
@Transactional
@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionCustomRepository transactionCustomRepository;

    @Override
    public Transaction findByTransactionId(Long id) {
        return transactionRepository.findByTransactionId(id);
    }

    @Override
    public Transaction addTransaction(Transaction newTransaction) {
        return transactionRepository.save(newTransaction);
    }

    @Override
    public List<Transaction> findTransactions(String username, Long startPosition, Long endPosition) {
        return transactionCustomRepository.getTransactions(username,startPosition, endPosition);
    }
}

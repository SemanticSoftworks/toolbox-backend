package com.example.service;

import com.example.domain.Transaction;

import java.util.List;

/**
 * Created by dani on 2017-02-09.
 */
public interface TransactionService {
    Transaction findByTransactionId(Long id);
    Transaction addTransaction(Transaction newTransaction);
    List<Transaction> findTransactions(String username, Long startPosition, Long endPosition);
}

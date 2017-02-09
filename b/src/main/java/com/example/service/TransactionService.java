package com.example.service;

import com.example.domain.Transaction;

/**
 * Created by dani on 2017-02-09.
 */
public interface TransactionService {
    Transaction findByTransactionId(Long id);
    Transaction addTransaction(Transaction newTransaction);
}

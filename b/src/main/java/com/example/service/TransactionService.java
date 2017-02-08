package com.example.service;

import com.example.domain.Transaction;

import java.util.List;

/**
 * Created by alica on 2017-02-08.
 * Good luck, Commander!
 */
public interface TransactionService {
    Transaction findTransactionById(Long transactionId);
    List<Transaction> findTransactionByUserId(Long userId);
    int addTransaction(Transaction transaction);
}

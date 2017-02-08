package com.example.service.impl;

import com.example.domain.Transaction;
import com.example.repositories.TransactionRepository;
import com.example.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by alica on 2017-02-08.
 * Good luck, COmmander!
 */

@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public Transaction findTransactionById(Long transactionId) {
   /*     Transaction tmpTransaction = transactionRepository.findTransactionById(transactionId);

        if(tmpTransaction != null){
            return tmpTransaction;
        } */
        return null;
    }//findTransactionById

    @Override
    public List<Transaction> findTransactionByUserId(Long userId) {
     /*   List<Transaction> tmpTransaction = transactionRepository.findTransactionByUserId(userId);

        if (tmpTransaction != null){
            return tmpTransaction;
        } */
        return null;
    }//findTransactionByUserId

    @Override
    public int addTransaction(Transaction transaction) {
     //   int status = transactionRepository.addTransaction(transaction);
     //   return status;
        return 0;
    }
}

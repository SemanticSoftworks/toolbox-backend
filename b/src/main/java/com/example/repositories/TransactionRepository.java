package com.example.repositories;

import com.example.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by alica on 2017-02-08.
 * Good luck, Commander!
 */

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Transaction findTransactionById(Long transactionId);
    List<Transaction> findTransactionByUserId(Long userId);
    int addTransaction(Transaction transaction);
}

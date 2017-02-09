package com.example.repositories;

import com.example.domain.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dani on 2017-02-09.
 */
@Repository
public interface TransactionCustomRepository{
    List<Transaction> getTransactions(String username,Long start, Long end);
}

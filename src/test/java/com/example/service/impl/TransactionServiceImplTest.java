package com.example.service.impl;

import com.example.domain.Transaction;
import com.example.domain.User;
import com.example.repositories.TransactionCustomRepository;
import com.example.repositories.TransactionRepository;
import com.example.service.TransactionService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
/**
 * Created by dani on 2017-02-16.
 */
@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceImplTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private TransactionCustomRepository transactionCustomRepository;

    @InjectMocks
    private TransactionService transactionService = new TransactionServiceImpl();

    @Before
    public void setUp(){ MockitoAnnotations.initMocks(this); }

    @Test
    public void addTransaction() throws Exception {

        User user = new User();
        user.setId(1L);
        user.setUsername("anders");
        user.setPassword("$2a$06$CDyAlnH2sR4taKM/ZoRIguvnY6/5rKHS.uDpJGvI8B.LQqD.OWQ5a");
        user.setEnabled(true);
        user.setEmail("anders@kth.se");

        Transaction transaction = new Transaction();
        transaction.setTransactionId(1L);
        transaction.setUser(user);
        transaction.setSum(0);
        transaction.setDescription("transaction");

        when(transactionRepository.save(transaction)).thenReturn(transaction);
        Transaction transactionCheck = transactionService.addTransaction(transaction);
        Assert.assertEquals(transaction,transactionCheck);
    }

    @Test
    public void findTransactions() throws Exception {
        List<Transaction> transactions = new ArrayList<>();

        User user = new User();
        user.setId(1L);
        user.setUsername("anders");
        user.setPassword("$2a$06$CDyAlnH2sR4taKM/ZoRIguvnY6/5rKHS.uDpJGvI8B.LQqD.OWQ5a");
        user.setEnabled(true);
        user.setEmail("anders@kth.se");

        Transaction transaction = new Transaction();
        transaction.setTransactionId(1L);
        transaction.setUser(user);
        transaction.setSum(0);
        transaction.setDescription("first transaction");

        Transaction t2 = new Transaction();
        t2.setTransactionId(2L);
        t2.setUser(user);
        t2.setSum(0);
        t2.setDescription("second transaction");

        transactions.add(transaction);
        transactions.add(t2);

        when(transactionCustomRepository.getTransactions("anders",0L,25L)).thenReturn(transactions);
        List<Transaction> returnredTransactions = transactionService.findTransactions("anders",0L,25L);
        Assert.assertEquals(transactions.size(),returnredTransactions.size());
    }
}
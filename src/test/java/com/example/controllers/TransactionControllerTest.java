package com.example.controllers;

import com.example.domain.Transaction;
import com.example.service.TransactionService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by dani on 2017-02-15.
 */
public class TransactionControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private TransactionController transactionController;

    @Mock
    private TransactionService transactionService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(transactionController).build();

        List<GrantedAuthority> authorityList = new ArrayList<>();
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_AUCTIONEER");

        authorityList.add(authority);
        UserDetails user = new User("anders", "23", true, true, true, true, authorityList);

        Authentication authentication = Mockito.mock(Authentication.class);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(securityContext.getAuthentication().getPrincipal()).thenReturn(user);
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    public void getTransaction() throws Exception {

        com.example.domain.User user = new com.example.domain.User();
        user.setId(1L);
        user.setUsername("anders");
        user.setPassword("123");
        user.setEmail("anders@kth.se");

        Transaction transaction = new Transaction();
        transaction.setTransactionId(1L);
        transaction.setDescription("First transaction");
        transaction.setSum(0);
        transaction.setUser(user);

        when(transactionService.findByTransactionId(1L)).thenReturn(transaction);
        mockMvc.perform(MockMvcRequestBuilders.get("/transaction/{id}",1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string("{\"transactionId\":1,\"user\":{\"id\":1,\"username\":\"anders\",\"firstname\":null,\"lastname\":null,\"email\":\"anders@kth.se\",\"userRoles\":[]},\"date\":null,\"description\":\"First transaction\",\"sum\":0}"));
    }

    @Test
    public void getTransactions() throws Exception {

        com.example.domain.User user1 = new com.example.domain.User();
        user1.setId(1L);
        user1.setUsername("anders");
        user1.setPassword("123");
        user1.setEmail("anders@kth.se");

        com.example.domain.User user2 = new com.example.domain.User();
        user2.setId(2L);
        user2.setUsername("dani");
        user2.setPassword("123");
        user2.setEmail("dani@kth.se");

        Transaction t1 = new Transaction();
        t1.setTransactionId(1L);
        t1.setUser(user1);
        t1.setDescription("first transaction");
        t1.setSum(0);

        Transaction t2 = new Transaction();
        t2.setTransactionId(2L);
        t2.setDescription("second transaction");
        t2.setSum(0);
        t2.setUser(user2);

        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(t1);
        transactionList.add(t2);

        when(transactionService.findTransactions("anders", 0L, 25L)).thenReturn(transactionList);

        mockMvc.perform(MockMvcRequestBuilders.get("/transaction/").param("startPosition", "0").param("endPosition", "25"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string("{\"transactionDTOList\":[{\"date\":null,\"description\":\"first transaction\",\"sum\":0,\"transactionId\":1},{\"date\":null,\"description\":\"second transaction\",\"sum\":0,\"transactionId\":2}]}"));
    }
}
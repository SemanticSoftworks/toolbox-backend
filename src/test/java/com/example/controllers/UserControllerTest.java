package com.example.controllers;

import com.example.domain.*;
import com.example.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by dani on 2017-02-15.
 */
@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
public class UserControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void getUser() throws Exception {
        Role role = new Role();
        role.setRole("AUCTIONEER");
        role.setRoleID(1);

        UserRole userRole = new UserRole();
        userRole.setRole(role);
        userRole.setUserRoleId(1L);

        Set<UserRole> userRoleSet = new HashSet<>();
        userRoleSet.add(userRole);

        List<Ad> adList = new ArrayList<>();

        Transaction transaction = new Transaction();
        transaction.setDescription("transaction");
        transaction.setTransactionId(1L);

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        User user = new User();
        user.setId(1L);
        user.setUsername("anders");
        user.setPassword("123");
        user.setEmail("anders@kth.se");
        user.setUserRole(userRoleSet);
        user.setTransactions(transactions);
        user.setAds(adList);

        when(userService.findUserById(1L)).thenReturn(user);
        mockMvc.perform(MockMvcRequestBuilders.get("/user/{id}",1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string("{\"id\":1,\"username\":\"anders\",\"firstname\":null,\"lastname\":null,\"email\":\"anders@kth.se\",\"userRoles\":[\"AUCTIONEER\"],\"ads\":[],\"transactions\":[{\"date\":null,\"description\":\"transaction\",\"sum\":0,\"transactionId\":1}]}"));

    }

    @Test
    public void login() throws Exception {}


    @Test
    public void register() throws Exception {
    }

    @Test
    public void updateUser() throws Exception {
    }

    @Test
    public void changePassword() throws Exception {
    }
}
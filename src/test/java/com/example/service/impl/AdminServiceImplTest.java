package com.example.service.impl;

import com.example.domain.*;
import com.example.repositories.*;
import com.example.service.AdminService;
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
public class AdminServiceImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private AdRepository adRepository;

    @Mock
    private UserCustomRepository userCustomRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AdminService adminService = new AdminServiceImpl();

    @Before
    public void setUp(){ MockitoAnnotations.initMocks(this); }

    @Test
    public void addCategory() throws Exception {
        Category category = new Category();
        category.setCategoryId(1L);
        category.setName("category");

        when(categoryRepository.findByName("category")).thenReturn(null);
        when(categoryRepository.save(category)).thenReturn(category);
        Category returnedCategory = adminService.addCategory(category);

        Assert.assertEquals(category.getName(), returnedCategory.getName());
    }

    @Test
    public void addRole() throws Exception {
        Role role = new Role();
        role.setRoleID(1);
        role.setRole("ADMIN");

        when(roleRepository.findByRole("ADMIN")).thenReturn(null);
        when(roleRepository.save(role)).thenReturn(role);
        Role returnedRole = adminService.addRole(role);
        Assert.assertEquals(role.getRole(), returnedRole.getRole());
    }

    @Test
    public void deleteTransactionById() throws Exception {
        Transaction transaction = new Transaction();
        transaction.setTransactionId(1L);
        transaction.setDescription("transaction");
        transaction.setSum(0);

        when(transactionRepository.findByTransactionId(1L)).thenReturn(transaction);
        Transaction returnedTransaction = adminService.deleteTransactionById(1L);
        Assert.assertEquals(transaction.getTransactionId(), returnedTransaction.getTransactionId());
    }

    @Test
    public void deleteAdById() throws Exception {
        Ad ad = new Ad();
        ad.setAdId(1);
        ad.setTitle("ad");
        ad.setDescription("first ad");

        when(adRepository.findByAdId(1)).thenReturn(ad);
        Ad returnedAd = adminService.deleteAdById(1);
        Assert.assertEquals(ad.getAdId(), returnedAd.getAdId());
    }

    @Test
    public void addUser() throws Exception {
        User user = new User();
        user.setUsername("anders");
        user.setPassword("123");
        user.setId(1L);
        user.setEnabled(true);
        user.setEmail("anders@kth.se");

        when(userRepository.findByUsername("anders")).thenReturn(null);
        when(userRepository.save(user)).thenReturn(user);
        User returnedUser = adminService.addUser(user);
        Assert.assertEquals(user.getUsername(), returnedUser.getUsername());
    }

    @Test
    public void findAllUsers() throws Exception {
        List<User> userList = new ArrayList<>();

        User user = new User();
        user.setUsername("anders");
        user.setPassword("123");
        user.setId(1L);
        user.setEnabled(true);
        user.setEmail("anders@kth.se");

        User u = new User();
        u.setId(2L);
        u.setUsername("dani");
        u.setPassword("123");
        u.setEnabled(true);
        u.setEmail("dani@kth.se");

        userList.add(u);
        userList.add(user);

        when(userCustomRepository.getUsers(0L,25L)).thenReturn(userList);
        List<User> returnedUserList = adminService.findAllUsers(0L,25L);
        Assert.assertEquals(userList.size(),returnedUserList.size());
    }
}
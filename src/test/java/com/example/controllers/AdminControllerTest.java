package com.example.controllers;

import com.example.domain.*;
import com.example.service.AdminService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by dani on 2017-02-16.
 */
@RunWith(MockitoJUnitRunner.class)
public class AdminControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private AdminController adminController;

    @Mock
    AdminService adminService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(adminController).build();

        List<GrantedAuthority> authorityList = new ArrayList<>();
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_AUCTIONEER");
        UserDetails user = new User("anders", "23", true, true, true, true, authorityList);
        authorityList.add(authority);

        Authentication authentication = Mockito.mock(Authentication.class);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(securityContext.getAuthentication().getPrincipal()).thenReturn(user);
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    public void getUsers() throws Exception {
        List<com.example.domain.User> userList = new ArrayList<>();

        Role role = new Role();
        role.setRole("AUCTIONEER");
        role.setRoleID(1);

        UserRole userRole = new UserRole();
        userRole.setRole(role);
        userRole.setUserRoleId(1L);

        Set<UserRole> userRoleSet = new HashSet<>();
        userRoleSet.add(userRole);

        com.example.domain.User user = new com.example.domain.User();
        user.setId(1L);
        user.setUsername("anders");
        user.setPassword("1234");
        user.setEnabled(true);
        user.setEmail("anders@kth.se");
        user.setUserRole(userRoleSet);

        com.example.domain.User user1 = new com.example.domain.User();
        user1.setId(2L);
        user1.setUsername("dani");
        user1.setPassword("1234");
        user1.setEnabled(true);
        user1.setEmail("dani@kth.se");
        user1.setUserRole(userRoleSet);

        userList.add(user);
        userList.add(user1);

        when(adminService.findAllUsers(0L,25L)).thenReturn(userList);
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/user").param("startPosition", "0").param("endPosition", "25"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string("[{\"id\":1,\"username\":\"anders\",\"password\":\"1234\",\"firstname\":null,\"lastname\":null,\"email\":\"anders@kth.se\",\"userRoles\":[\"AUCTIONEER\"],\"enabled\":true},{\"id\":2,\"username\":\"dani\",\"password\":\"1234\",\"firstname\":null,\"lastname\":null,\"email\":\"dani@kth.se\",\"userRoles\":[\"AUCTIONEER\"],\"enabled\":true}]"));
    }

    @Test
    public void accountActivation() throws Exception {

        Role role = new Role();
        role.setRole("AUCTIONEER");
        role.setRoleID(1);

        UserRole userRole = new UserRole();
        userRole.setRole(role);
        userRole.setUserRoleId(1L);

        Set<UserRole> userRoleSet = new HashSet<>();
        userRoleSet.add(userRole);

        com.example.domain.User user = new com.example.domain.User();
        user.setId(1L);
        user.setUsername("anders");
        user.setEnabled(true);
        user.setEmail("anders@kth.se");
        user.setUserRole(userRoleSet);

        when(adminService.findUserById(1L)).thenReturn(user);
        when(adminService.updateUser(user)).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.post("/admin/user/accountActivation/{id}",1L).param("enable", "false"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string("{\"id\":1,\"username\":\"anders\",\"password\":null,\"firstname\":null,\"lastname\":null,\"email\":\"anders@kth.se\",\"userRoles\":[\"AUCTIONEER\"],\"enabled\":false}"));
    }

    @Test
    public void deleteTransaction() throws Exception {
        com.example.domain.User user1 = new com.example.domain.User();
        user1.setId(1L);
        user1.setUsername("anders");
        user1.setPassword("123");
        user1.setEmail("anders@kth.se");

        Transaction t1 = new Transaction();
        t1.setTransactionId(1L);
        t1.setUser(user1);
        t1.setDescription("first transaction");
        t1.setSum(0);

        when(adminService.deleteTransactionById(1L)).thenReturn(t1);
        mockMvc.perform(MockMvcRequestBuilders.delete("/admin/transaction/{id}",1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string("{\"date\":null,\"description\":\"first transaction\",\"sum\":0,\"transactionId\":1}"));
    }

    @Test
    public void deleteAd() throws Exception {
        com.example.domain.User user = new com.example.domain.User();
        user.setId(1L);
        user.setUsername("anders");
        user.setPassword("123");
        user.setEmail("anders@kth.se");

        Category category = new Category();
        category.setCategoryId(1L);
        category.setName("category");

        Ad ad1 = new Ad();
        ad1.setAdId(1);
        ad1.setUser(user);
        ad1.setCategory(category);
        ad1.setTitle("first");
        ad1.setDescription("first ad");

        when(adminService.deleteAdById(1L)).thenReturn(ad1);
        mockMvc.perform(MockMvcRequestBuilders.delete("/admin/ad/{id}",1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string("{\"adId\":1,\"category\":{\"categoryId\":1,\"name\":\"category\"},\"title\":\"first\",\"description\":\"first ad\",\"duration\":null,\"user\":1}"));
    }
}